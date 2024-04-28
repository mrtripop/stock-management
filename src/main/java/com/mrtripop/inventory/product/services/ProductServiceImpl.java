package com.mrtripop.inventory.product.services;

import com.mrtripop.inventory.exception.GlobalThrowable;
import com.mrtripop.inventory.product.interfaces.ProductService;
import com.mrtripop.inventory.product.models.Product;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

  private final ProductService productService;

  public ProductServiceImpl(DatabaseManagementImpl productService) {
    this.productService = productService;
  }

  @Override
  public List<Product> getAllProducts(Integer page, Integer size, String orderBy)
      throws GlobalThrowable {
    return productService.getAllProducts(page, size, orderBy);
  }

  @Override
  public Product getProductById(Long id) throws GlobalThrowable {
    return productService.getProductById(id);
  }

  @Override
  public Product createProduct(Product newProduct) throws GlobalThrowable {
    // send email or produce queue or something here
    return productService.createProduct(newProduct);
  }

  @Override
  public Product updateProduct(Long id, Product updateProduct) throws GlobalThrowable {
    // send email or produce queue or something here
    return productService.updateProduct(id, updateProduct);
  }

  @Override
  public void deleteProduct(Long id) throws GlobalThrowable {
    // send email or produce queue or something here
    productService.deleteProduct(id);
  }
}
