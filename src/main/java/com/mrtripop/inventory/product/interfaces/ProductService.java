package com.mrtripop.inventory.product.interfaces;

import com.mrtripop.inventory.exception.GlobalThrowable;
import com.mrtripop.inventory.product.models.Product;
import java.util.List;

public interface ProductService {
  List<Product> getAllProducts(Integer page, Integer size, String orderBy) throws GlobalThrowable;

  Product getProductById(Long id) throws GlobalThrowable;

  Product createProduct(Product newProduct) throws GlobalThrowable;

  Product updateProduct(Long id, Product updateProduct) throws GlobalThrowable;

  void deleteProduct(Long id) throws GlobalThrowable;
}
