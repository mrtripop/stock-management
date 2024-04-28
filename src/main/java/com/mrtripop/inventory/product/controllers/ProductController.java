package com.mrtripop.inventory.product.controllers;

import com.mrtripop.inventory.constant.BaseStatusCode;
import com.mrtripop.inventory.exception.GlobalThrowable;
import com.mrtripop.inventory.model.ResponseBody;
import com.mrtripop.inventory.product.constant.ErrorCode;
import com.mrtripop.inventory.product.constant.SuccessCode;
import com.mrtripop.inventory.product.interfaces.ProductService;
import com.mrtripop.inventory.product.models.Product;
import com.mrtripop.inventory.product.services.ProductServiceImpl;
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
      @RequestParam(name = "order_by", defaultValue = "ASC", required = false) String orderBy) {
    try {
      List<Product> products = this.productService.getAllProducts(page, size, orderBy);
      BaseStatusCode successCode = SuccessCode.PRO2001_GET_ALL_PRODUCTS_IS_SUCCESS;
      return ResponseBody.builder()
          .code(successCode.getCode())
          .message(successCode.getMessage())
          .data(products)
          .build()
          .buildResponseEntity(HttpStatus.OK);

    } catch (Exception e) {
      BaseStatusCode errorCode = ErrorCode.PRO1001_CANNOT_GET_ALL_PRODUCTS;
      return ResponseBody.builder()
          .code(errorCode.getCode())
          .message(errorCode.getMessage())
          .build()
          .buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    } catch (GlobalThrowable e) {
      BaseStatusCode errorCode = e.getErrorCode();
      return ResponseBody.builder()
          .code(errorCode.getCode())
          .message(errorCode.getMessage())
          .build()
          .buildResponseEntity(e.getHttpStatus());
    }
  }

  @GetMapping("/{product_id}")
  public ResponseEntity<Object> getProductById(@PathVariable(name = "product_id") Long productId) {
    try {
      Product product = this.productService.getProductById(productId);
      BaseStatusCode successCode = SuccessCode.PRO2002_GET_PRODUCTS_BY_ID_IS_SUCCESS;
      return ResponseBody.builder()
          .code(successCode.getCode())
          .message(successCode.getMessage())
          .data(product)
          .build()
          .buildResponseEntity(HttpStatus.OK);

    } catch (Exception e) {
      BaseStatusCode errorCode = ErrorCode.PRO1001_CANNOT_GET_ALL_PRODUCTS;
      return ResponseBody.builder()
          .code(errorCode.getCode())
          .message(errorCode.getMessage())
          .build()
          .buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    } catch (GlobalThrowable e) {
      BaseStatusCode errorCode = e.getErrorCode();
      return ResponseBody.builder()
          .code(errorCode.getCode())
          .message(errorCode.getMessage())
          .build()
          .buildResponseEntity(e.getHttpStatus());
    }
  }

  @PostMapping
  public ResponseEntity<Object> createNewProduct(@RequestBody Product product) {
    try {
      Product createdProduct = this.productService.createProduct(product);
      BaseStatusCode successCode = SuccessCode.PRO2003_CREATE_NEW_PRODUCT_IS_SUCCESS;
      return ResponseBody.builder()
          .code(successCode.getCode())
          .message(successCode.getMessage())
          .data(createdProduct)
          .build()
          .buildResponseEntity(HttpStatus.CREATED);

    } catch (Exception e) {
      BaseStatusCode errorCode = ErrorCode.PRO1001_CANNOT_GET_ALL_PRODUCTS;
      return ResponseBody.builder()
          .code(errorCode.getCode())
          .message(errorCode.getMessage())
          .build()
          .buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    } catch (GlobalThrowable e) {
      BaseStatusCode errorCode = e.getErrorCode();
      return ResponseBody.builder()
          .code(errorCode.getCode())
          .message(errorCode.getMessage())
          .build()
          .buildResponseEntity(e.getHttpStatus());
    }
  }

  @PutMapping("/{product_id}")
  public ResponseEntity<Object> updateProductById(
      @PathVariable(name = "product_id") Long productId, @RequestBody Product product) {
    try {
      Product updatedProduct = productService.updateProduct(productId, product);
      BaseStatusCode successCode = SuccessCode.PRO2004_UPDATE_PRODUCT_IS_SUCCESS;
      return ResponseBody.builder()
          .code(successCode.getCode())
          .message(successCode.getMessage())
          .data(updatedProduct)
          .build()
          .buildResponseEntity(HttpStatus.OK);

    } catch (Exception e) {
      BaseStatusCode errorCode = ErrorCode.PRO1001_CANNOT_GET_ALL_PRODUCTS;
      return ResponseBody.builder()
          .code(errorCode.getCode())
          .message(errorCode.getMessage())
          .build()
          .buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    } catch (GlobalThrowable e) {
      BaseStatusCode errorCode = e.getErrorCode();
      return ResponseBody.builder()
          .code(errorCode.getCode())
          .message(errorCode.getMessage())
          .build()
          .buildResponseEntity(e.getHttpStatus());
    }
  }

  @DeleteMapping("/{product_id}")
  public ResponseEntity<Object> deleteProductById(
      @PathVariable(name = "product_id") Long productId) {
    try {
      productService.deleteProduct(productId);
      BaseStatusCode successCode = SuccessCode.PRO2005_DELETE_PRODUCT_IS_SUCCESS;
      return ResponseBody.builder()
          .code(successCode.getCode())
          .message(successCode.getMessage())
          .build()
          .buildResponseEntity(HttpStatus.OK);

    } catch (Exception e) {
      BaseStatusCode errorCode = ErrorCode.PRO1001_CANNOT_GET_ALL_PRODUCTS;
      return ResponseBody.builder()
          .code(errorCode.getCode())
          .message(errorCode.getMessage())
          .build()
          .buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    } catch (GlobalThrowable e) {
      BaseStatusCode errorCode = e.getErrorCode();
      return ResponseBody.builder()
          .code(errorCode.getCode())
          .message(errorCode.getMessage())
          .build()
          .buildResponseEntity(e.getHttpStatus());
    }
  }
}
