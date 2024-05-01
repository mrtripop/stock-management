package com.mrtripop.inventory.product.controllers;

import com.mrtripop.inventory.constant.BaseStatusCode;
import com.mrtripop.inventory.exception.GlobalThrowable;
import com.mrtripop.inventory.model.ResponseBody;
import com.mrtripop.inventory.product.constant.SuccessCode;
import com.mrtripop.inventory.product.interfaces.ProductHistoryService;
import com.mrtripop.inventory.product.models.ProductDTO;
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
      @PathVariable(name = "product_code") String productCode)
      throws GlobalThrowable {
    List<ProductDTO> productHistories =
        this.productService.getAllProductsHistory(page, size, orderBy);
    BaseStatusCode statusCode = SuccessCode.PRO2006_GET_ALL_PRODUCT_HISTORIES_IS_SUCCESS;
    return ResponseBody.builder()
        .code(statusCode.getCode())
        .message(statusCode.getMessage())
        .data(productHistories)
        .build()
        .buildResponseEntity(HttpStatus.OK);
  }
}
