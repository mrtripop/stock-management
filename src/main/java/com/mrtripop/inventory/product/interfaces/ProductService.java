package com.mrtripop.inventory.product.interfaces;

import com.mrtripop.inventory.exception.GlobalThrowable;
import com.mrtripop.inventory.product.models.ProductDTO;
import java.util.List;

public interface ProductService {
  List<ProductDTO> getAllProducts(Integer page, Integer size, String orderBy) throws GlobalThrowable;

  ProductDTO getProductById(Long id) throws GlobalThrowable;

  ProductDTO createProduct(ProductDTO newProduct) throws GlobalThrowable;

  ProductDTO updateProduct(Long id, ProductDTO updateProduct) throws GlobalThrowable;

  void deleteProduct(Long id) throws GlobalThrowable;
}
