package com.mrtripop.inventory.product.controllers;

import com.mrtripop.inventory.constant.BaseStatusCode;
import com.mrtripop.inventory.exception.GlobalThrowable;
import com.mrtripop.inventory.model.ResponseBody;
import com.mrtripop.inventory.product.constant.ErrorCode;
import com.mrtripop.inventory.product.interfaces.ProductHistoryService;
import com.mrtripop.inventory.product.models.ProductHistory;
import com.mrtripop.inventory.product.services.ProductHistoryServiceImpl;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/inventory/products")
public class ProductHistoryController {

  private final ProductHistoryService productService;

  public ProductHistoryController(ProductHistoryServiceImpl productService) {
    this.productService = productService;
  }

  @GetMapping("/{product_code}/histories")
  public ResponseEntity<Object> getAllProductsHistory(
      @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
      @RequestParam(name = "size", defaultValue = "200", required = false) Integer size,
      @RequestParam(name = "order_by", defaultValue = "ASC", required = false) String orderBy,
      @PathVariable(name = "product_code") String productCode) {
    try {
      log.info(
          "Get all products history by page={}, size={}, and order by={}", page, size, orderBy);
      List<ProductHistory> result = this.productService.getAllProductsHistory(page, size, orderBy);
      return new ResponseEntity<>(result, HttpStatus.OK);

    } catch (Exception e) {
      log.error("Get all products history API error: {}", e.getMessage());
      BaseStatusCode errorCode = ErrorCode.PRO1001_CANNOT_GET_ALL_PRODUCTS;
      return ResponseBody.builder()
          .code(errorCode.getCode())
          .message(errorCode.getMessage())
          .error(e.getCause())
          .build()
          .buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    } catch (GlobalThrowable e) {
      BaseStatusCode errorCode = e.getErrorCode();
      log.error(
          "Get all products history API error with code: {} and message: {}",
          errorCode.getCode(),
          errorCode.getMessage());
      return ResponseBody.builder()
          .code(errorCode.getCode())
          .message(errorCode.getMessage())
          .error(e.getCause())
          .build()
          .buildResponseEntity(e.getHttpStatus());
    }
  }
}
