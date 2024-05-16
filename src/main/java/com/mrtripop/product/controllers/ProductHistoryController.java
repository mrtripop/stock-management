package com.mrtripop.product.controllers;

import com.mrtripop.constant.BaseStatusCode;
import com.mrtripop.exception.GlobalThrowable;
import com.mrtripop.model.ResponseBody;
import com.mrtripop.product.constant.ErrorCode;
import com.mrtripop.product.constant.SuccessCode;
import com.mrtripop.product.models.ProductDTO;
import com.mrtripop.product.services.ProductHistoryServiceImpl;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/inventory/products/histories")
public class ProductHistoryController {

  private final ProductHistoryServiceImpl productService;

  public ProductHistoryController(ProductHistoryServiceImpl productService) {
    this.productService = productService;
  }

  @GetMapping
  public ResponseEntity<Object> getProductsHistories(
      @RequestParam(name = "page", defaultValue = "1", required = false)
          @Min(value = 1, message = "Page query param must not less than one")
          @NotNull(message = "Page query param must not be null")
          Integer page,
      @RequestParam(name = "size", defaultValue = "200", required = false)
          @Min(value = 1, message = "Size query param must not less than one")
          @NotNull(message = "Size query param must not be null")
          Integer size,
      @RequestParam(name = "order_by", defaultValue = "ASC", required = false)
          @NotNull(message = "Order by query param must not be null")
          Sort.Direction orderBy)
      throws GlobalThrowable {
    try {
      List<ProductDTO> productHistories =
          this.productService.getProductsHistories(page, size, orderBy);
      BaseStatusCode statusCode = SuccessCode.PRO2006_GET_ALL_PRODUCT_HISTORIES_IS_SUCCESS;
      return ResponseBody.builder()
          .code(statusCode.getCode())
          .message(statusCode.getMessage())
          .data(productHistories)
          .build()
          .buildResponseEntity(HttpStatus.OK);
    } catch (Exception e) {
      log.error("Cannot get all product histories: {}", e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.PRO1007_CANNOT_GET_ALL_PRODUCT_HISTORIES, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{product_code}")
  public ResponseEntity<Object> getProductsHistoriesByCode(
      @RequestParam(name = "page", defaultValue = "1", required = false)
          @Min(value = 1, message = "Page query param must not less than one")
          @NotNull(message = "Page query param must not be null")
          Integer page,
      @RequestParam(name = "size", defaultValue = "200", required = false)
          @Min(value = 1, message = "Size query param must not less than one")
          @NotNull(message = "Size query param must not be null")
          Integer size,
      @RequestParam(name = "order_by", defaultValue = "ASC", required = false)
          @NotNull(message = "Order by query param must not be null")
          Sort.Direction orderBy,
      @PathVariable(name = "product_code")
          @NotBlank(message = "Product code query param must not be blank")
          @NotNull(message = "Product code query param must not be null")
          @NotEmpty(message = "Product code query param must not be empty")
          String productCode)
      throws GlobalThrowable {
    try {
      List<ProductDTO> productHistories =
          this.productService.getProductHistoriesByCode(productCode, page, size, orderBy);
      BaseStatusCode statusCode = SuccessCode.PRO2006_GET_ALL_PRODUCT_HISTORIES_IS_SUCCESS;
      return ResponseBody.builder()
          .code(statusCode.getCode())
          .message(statusCode.getMessage())
          .data(productHistories)
          .build()
          .buildResponseEntity(HttpStatus.OK);
    } catch (Exception e) {
      log.error("Cannot get all product histories: {}", e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.PRO1011_CANNOT_GET_PRODUCT_HISTORIES_BY_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
