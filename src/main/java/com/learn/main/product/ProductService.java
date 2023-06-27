package com.learn.main.product;

import com.learn.helper.DatabaseHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> getProducts(Integer page, Integer size, String orderBy) {
    try {
      Pageable pageSize = DatabaseHelper.initPageableWithSort(page, size, orderBy);
      Page<Product> result = productRepository.findAll(pageSize);
      return result.getContent();
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      log.error(error);
      throw new RuntimeException("GetProductsException", e.getCause());
    }
  }

  public Product getProductById(Long productId) {
    try {
      Optional<Product> result = productRepository.findById(productId);
      return result.orElse(null);
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      log.error(error);
      throw new RuntimeException("GetProductByIdException", e.getCause());
    }
  }

  public Product createNewProduct(Product product) {
    try {
      product.setCreatedAt(ZonedDateTime.now());
      product.setUpdatedAt(ZonedDateTime.now());
      return productRepository.save(product);
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      log.error(error);
      throw new RuntimeException("CreateProductException", e.getCause());
    }
  }

  public Product updateProduct(Long productId, Product product) {
    try {
      Optional<Product> result = productRepository.findById(productId);
      if (result.isPresent()) {
        Product origin = result.get();
        origin.setType(product.getType());
        origin.setTitle(product.getTitle());
        origin.setSummary(product.getSummary());
        origin.setContent(product.getContent());
        origin.setUpdatedAt(ZonedDateTime.now());
        return productRepository.save(origin);
      } else {
        return productRepository.save(product);
      }
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      log.error(error);
      throw new RuntimeException("UpdateProductException", e.getCause());
    }
  }

  public boolean deleteProduct(Long productId) {
    try {
      Optional<Product> result = productRepository.findById(productId);
      if (result.isPresent()) {
        productRepository.deleteById(productId);
        return true;
      }
      return false;
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      log.error(error);
      throw new RuntimeException("DeleteProductException", e.getCause());
    }
  }
}
