package com.mrtripop.product.services;

import com.mrtripop.exception.GlobalThrowable;
import com.mrtripop.product.component.ProductProcessor;
import com.mrtripop.product.constant.ErrorCode;
import com.mrtripop.product.interfaces.ProductService;
import com.mrtripop.product.models.ProductDTO;
import com.mrtripop.product.models.db.Product;
import com.mrtripop.product.models.db.ProductHistory;
import com.mrtripop.product.repository.ProductHistoryRepository;
import com.mrtripop.product.repository.ProductRepository;
import com.mrtripop.product.util.ProductUtil;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DatabaseManagementImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductHistoryRepository productHistoryRepository;

  public DatabaseManagementImpl(
      ProductRepository productRepository, ProductHistoryRepository productHistoryRepository) {
    this.productRepository = productRepository;
    this.productHistoryRepository = productHistoryRepository;
  }

  @Override
  public List<ProductDTO> getProducts(Integer page, Integer size, Sort.Direction orderBy) {
    Pageable pageSize = ProductUtil.initPageableWithSort(page, size, orderBy);
    Page<Product> productPages = productRepository.findAll(pageSize);
    List<Product> products = productPages.getContent();
    return products.stream().map(ProductProcessor::mapToProductDTO).toList();
  }

  @Override
  public ProductDTO getProductById(Long id) throws GlobalThrowable {
    Optional<Product> haveProduct = productRepository.findById(id);
    Product product =
        haveProduct.orElseThrow(
            () ->
                new GlobalThrowable(
                    ErrorCode.PRO1003_CANNOT_GET_PRODUCT_BY_ID, HttpStatus.NOT_FOUND));
    return ProductProcessor.mapToProductDTO(product);
  }

  @Override
  @Transactional(rollbackOn = {GlobalThrowable.class})
  public ProductDTO createProduct(ProductDTO productDTO) throws GlobalThrowable {
    try {
      Product product = ProductProcessor.mapToProduct(productDTO);
      ProductHistory productHistory = ProductProcessor.toProductHistory(product);
      productHistoryRepository.save(productHistory);
      Product productSaved = productRepository.save(product);
      return ProductProcessor.mapToProductDTO(productSaved);
    } catch (Exception e) {
      log.error("Cannot create a new product: {}", e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.PRO1002_CANNOT_CREATE_NEW_PRODUCT, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  @Transactional(rollbackOn = {GlobalThrowable.class})
  public ProductDTO updateProduct(Long id, ProductDTO updateProduct) throws GlobalThrowable {
    try {
      ProductDTO existingProduct = getProductById(id);
      ProductProcessor.updateProduct(existingProduct, updateProduct);
      return createProduct(existingProduct);
    } catch (Exception e) {
      log.error("Cannot update product ID='{}': {}", id, e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.PRO1004_CANNOT_UPDATE_EXISTING_PRODUCT, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  @Transactional(rollbackOn = {GlobalThrowable.class})
  public void deleteProduct(Long id) throws GlobalThrowable {
    try {
      ProductDTO existingProduct = getProductById(id);
      productRepository.deleteById(id);
      ProductHistory producthistory = ProductProcessor.toProductHistory(existingProduct);
      producthistory.setIsActive(false);
      productHistoryRepository.save(producthistory);
    } catch (Exception e) {
      log.error("Cannot delete product ID='{}': {}", id, e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.PRO1005_CANNOT_DELETE_EXISTING_PRODUCT, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
