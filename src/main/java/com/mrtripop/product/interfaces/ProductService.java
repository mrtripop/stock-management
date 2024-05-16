package com.mrtripop.product.interfaces;

import com.mrtripop.exception.GlobalThrowable;
import com.mrtripop.product.models.ProductDTO;
import java.util.List;

import org.springframework.data.domain.Sort;

public interface ProductService {
  List<ProductDTO> getProducts(Integer page, Integer size, Sort.Direction orderBy) throws GlobalThrowable;

  ProductDTO getProductById(Long id) throws GlobalThrowable;

  ProductDTO createProduct(ProductDTO newProduct) throws GlobalThrowable;

  ProductDTO updateProduct(Long id, ProductDTO updateProduct) throws GlobalThrowable;

  void deleteProduct(Long id) throws GlobalThrowable;
}
