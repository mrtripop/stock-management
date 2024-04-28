package com.mrtripop.inventory.product.services;

import com.mrtripop.inventory.exception.GlobalThrowable;
import com.mrtripop.inventory.product.component.ProductProcessor;
import com.mrtripop.inventory.product.constant.ErrorCode;
import com.mrtripop.inventory.product.interfaces.ProductHistoryService;
import com.mrtripop.inventory.product.interfaces.ProductService;
import com.mrtripop.inventory.product.models.Product;
import com.mrtripop.inventory.product.models.ProductHistory;
import com.mrtripop.inventory.product.repository.ProductHistoryRepository;
import com.mrtripop.inventory.product.repository.ProductRepository;
import com.mrtripop.inventory.util.DatabaseHelper;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DatabaseManagementImpl implements ProductHistoryService, ProductService {

  private final ProductRepository productRepository;
  private final ProductHistoryRepository productHistoryRepository;

  public DatabaseManagementImpl(
      ProductRepository productRepository, ProductHistoryRepository productHistoryRepository) {
    this.productRepository = productRepository;
    this.productHistoryRepository = productHistoryRepository;
  }

  @Override
  public List<ProductHistory> getAllProductsHistory(Integer page, Integer size, String orderBy) {
    Pageable pageSize = DatabaseHelper.initPageableWithSort(page, size, orderBy);
    Page<ProductHistory> productPages = productHistoryRepository.findAll(pageSize);
    return productPages.getContent();
  }

  @Override
  public List<Product> getAllProducts(Integer page, Integer size, String orderBy) {
    Pageable pageSize = DatabaseHelper.initPageableWithSort(page, size, orderBy);
    Page<Product> productPages = productRepository.findAll(pageSize);
    return productPages.getContent();
  }

  @Override
  public Product getProductById(Long id) throws GlobalThrowable {
    Optional<Product> product = productRepository.findById(id);
    return product.orElseThrow(
        () ->
            new GlobalThrowable(ErrorCode.PRO1003_CANNOT_GET_PRODUCT_BY_ID, HttpStatus.NOT_FOUND));
  }

  @Override
  @Transactional(rollbackOn = {GlobalThrowable.class})
  public Product createProduct(Product product) throws GlobalThrowable {
    try {
      ProductHistory productHistory = ProductProcessor.toProductHistory(product);
      productHistoryRepository.save(productHistory);
      return productRepository.save(product);
    } catch (Exception e) {
      throw new GlobalThrowable(
          ErrorCode.PRO1002_CANNOT_CREATE_NEW_PRODUCT, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  @Transactional(rollbackOn = {GlobalThrowable.class})
  public Product updateProduct(Long id, Product updateProduct) throws GlobalThrowable {
    try {
      Product existingProduct = getProductById(id);
      ProductProcessor.updateProduct(existingProduct, updateProduct);
      return createProduct(existingProduct);
    } catch (Exception e) {
      throw new GlobalThrowable(
          ErrorCode.PRO1004_CANNOT_UPDATE_EXISTING_PRODUCT, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  @Transactional(rollbackOn = {GlobalThrowable.class})
  public void deleteProduct(Long id) throws GlobalThrowable {
    try {
      Product existingProduct = getProductById(id);
      productRepository.deleteById(id);
      ProductHistory producthistory = ProductProcessor.toProductHistory(existingProduct);
      producthistory.setIsActive(false);
      productHistoryRepository.save(producthistory);
    } catch (Exception e) {
      throw new GlobalThrowable(
          ErrorCode.PRO1005_CANNOT_UPDATE_EXISTING_PRODUCT, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
