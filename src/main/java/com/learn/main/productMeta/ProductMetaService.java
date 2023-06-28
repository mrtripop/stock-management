package com.learn.main.productMeta;

import com.learn.helper.DatabaseHelper;
import com.learn.main.product.Product;
import com.learn.main.product.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductMetaService {

  private final ProductMetaRepository productMetaRepository;
  private final ProductRepository productRepository;

  public ProductMetaService(
      ProductMetaRepository productMetaRepository, ProductRepository productRepository) {
    this.productMetaRepository = productMetaRepository;
    this.productRepository = productRepository;
  }

  public List<ProductMeta> getProductMeta(
      Long productId, Integer page, Integer size, String orderBy) {
    try {
      Pageable pageSize = DatabaseHelper.initPageableWithSort(page, size, orderBy);
      Page<ProductMeta> result = productMetaRepository.findAllByProductId(productId, pageSize);
      return result.stream().toList();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public ProductMeta getProductMetaById(Long productId, Long metaId) {
    try {
      Optional<Product> existedProduct = productRepository.findById(productId);
      if (existedProduct.isEmpty()) return null;
      Optional<ProductMeta> productMeta =
          productMetaRepository.findByProductIdAndId(productId, metaId);
      return productMeta.orElse(null);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public ProductMeta createNewProductMeta(Long productId, ProductMeta meta) {
    try {
      Optional<Product> product = productRepository.findById(productId);
      if (product.isEmpty()) return null;
      meta.setProduct(product.get());
      return productMetaRepository.save(meta);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public ProductMeta updateProductMeta(Long productId, Long metaId, ProductMeta meta) {
    try {
      Optional<Product> product = productRepository.findById(productId);
      if (product.isEmpty()) return null;

      Optional<ProductMeta> existedProductMeta =
          productMetaRepository.findByProductIdAndId(productId, metaId);
      if (existedProductMeta.isPresent()) {
        ProductMeta origin = existedProductMeta.get();
        origin.setKey(meta.getKey());
        origin.setContent(meta.getContent());
        return productMetaRepository.save(origin);
      } else {
        return createNewProductMeta(productId, meta);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public boolean deleteProductMetaById(Long productId, Long metaId) {
    try {
      Optional<ProductMeta> meta = productMetaRepository.findByProductIdAndId(productId, metaId);
      if (meta.isEmpty()) return false;
      productMetaRepository.deleteById(metaId);
      return true;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
