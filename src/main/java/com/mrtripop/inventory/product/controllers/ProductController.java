package com.mrtripop.inventory.product.controllers;

import com.mrtripop.inventory.constant.BaseStatusCode;
import com.mrtripop.inventory.exception.GlobalThrowable;
import com.mrtripop.inventory.model.ResponseBody;
import com.mrtripop.inventory.product.constant.SuccessCode;
import com.mrtripop.inventory.product.interfaces.ProductService;
import com.mrtripop.inventory.product.models.ProductDTO;
import com.mrtripop.inventory.product.services.ProductServiceImpl;
import jakarta.validation.Valid;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/inventory/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductServiceImpl productService) {
    this.productService = productService;
  }

  @GetMapping
  public ResponseEntity<Object> getAllProducts(
      @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
      @RequestParam(name = "size", defaultValue = "200", required = false) Integer size,
      @RequestParam(name = "order_by", defaultValue = "ASC", required = false) String orderBy)
      throws GlobalThrowable {
    List<ProductDTO> products = this.productService.getAllProducts(page, size, orderBy);
    BaseStatusCode successCode = SuccessCode.PRO2001_GET_ALL_PRODUCTS_IS_SUCCESS;
    return ResponseBody.builder()
        .code(successCode.getCode())
        .message(successCode.getMessage())
        .data(products)
        .build()
        .buildResponseEntity(HttpStatus.OK);
  }

  @GetMapping("/{product_id}")
  public ResponseEntity<Object> getProductById(@PathVariable(name = "product_id") Long productId)
      throws GlobalThrowable {
    ProductDTO product = this.productService.getProductById(productId);
    BaseStatusCode successCode = SuccessCode.PRO2002_GET_PRODUCTS_BY_ID_IS_SUCCESS;
    return ResponseBody.builder()
        .code(successCode.getCode())
        .message(successCode.getMessage())
        .data(product)
        .build()
        .buildResponseEntity(HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Object> createNewProduct(@RequestBody @Valid ProductDTO product)
      throws GlobalThrowable {
    ProductDTO createdProduct = this.productService.createProduct(product);
    BaseStatusCode successCode = SuccessCode.PRO2003_CREATE_NEW_PRODUCT_IS_SUCCESS;
    return ResponseBody.builder()
        .code(successCode.getCode())
        .message(successCode.getMessage())
        .data(createdProduct)
        .build()
        .buildResponseEntity(HttpStatus.CREATED);
  }

  @PutMapping("/{product_id}")
  public ResponseEntity<Object> updateProductById(
      @PathVariable(name = "product_id") Long productId, @RequestBody @Valid ProductDTO product)
      throws GlobalThrowable {
    ProductDTO updatedProduct = productService.updateProduct(productId, product);
    BaseStatusCode successCode = SuccessCode.PRO2004_UPDATE_PRODUCT_IS_SUCCESS;
    return ResponseBody.builder()
        .code(successCode.getCode())
        .message(successCode.getMessage())
        .data(updatedProduct)
        .build()
        .buildResponseEntity(HttpStatus.OK);
  }

  @DeleteMapping("/{product_id}")
  public ResponseEntity<Object> deleteProductById(@PathVariable(name = "product_id") Long productId)
      throws GlobalThrowable {
    productService.deleteProduct(productId);
    BaseStatusCode successCode = SuccessCode.PRO2005_DELETE_PRODUCT_IS_SUCCESS;
    return ResponseBody.builder()
        .code(successCode.getCode())
        .message(successCode.getMessage())
        .build()
        .buildResponseEntity(HttpStatus.OK);
  }
}
