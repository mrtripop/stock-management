package com.mrtripop.inventory.controller;

import com.mrtripop.inventory.service.ProductService;
import com.mrtripop.inventory.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public ResponseEntity<List<Product>> getProducts(
      @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
      @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
      @RequestParam(name = "orderBy", defaultValue = "ASC", required = false) String orderBy) {
    try {
      List<Product> result = this.productService.getProducts(page, size, orderBy);
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{productId}")
  public ResponseEntity<Product> getProductById(@PathVariable(name = "productId") Long productId) {
    try {
      Product result = this.productService.getProductById(productId);
      if (result != null) {
        return new ResponseEntity<>(result, HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping
  public ResponseEntity<Product> createNewProduct(@RequestBody Product product) {
    try {
      Product result = this.productService.createNewProduct(product);
      return new ResponseEntity<>(result, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{productId}")
  public ResponseEntity<Product> updateProductById(
      @PathVariable(name = "productId") Long productId, @RequestBody Product product) {
    try {
      Product result = productService.updateProduct(productId, product);
      return new ResponseEntity<>(result, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{productId}")
  public ResponseEntity<?> deleteProductById(@PathVariable(name = "productId") Long productId) {
    try {
      boolean result = productService.deleteProduct(productId);
      if (result) return new ResponseEntity<>(HttpStatus.OK);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
