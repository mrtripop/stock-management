package com.learn.main.productMeta;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/inventory/products")
public class ProductMetaController {

  private final ProductMetaService productMetaService;

  public ProductMetaController(ProductMetaService productMetaService) {
    this.productMetaService = productMetaService;
  }

  @GetMapping("/{productId}/metas")
  public ResponseEntity<List<ProductMeta>> getProductMeta(
      @PathVariable(name = "productId") Long productId,
      @RequestParam(name = "page", defaultValue = "0") Integer page,
      @RequestParam(name = "size", defaultValue = "10") Integer size,
      @RequestParam(name = "orderBy", defaultValue = "ASC") String orderBy) {
    try {
      List<ProductMeta> result = productMetaService.getProductMeta(productId, page, size, orderBy);
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{productId}/metas/{metaId}")
  public ResponseEntity<ProductMeta> getProductMetaById(
      @PathVariable(name = "productId") Long productId,
      @PathVariable(name = "metaId") Long metaId) {
    try {
      ProductMeta result = productMetaService.getProductMetaById(productId, metaId);
      if (result == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/{productId}/metas")
  public ResponseEntity<ProductMeta> createNewProductMeta(
      @PathVariable(name = "productId") Long productId, @RequestBody ProductMeta meta) {
    try {
      ProductMeta result = productMetaService.createNewProductMeta(productId, meta);
      if (result != null) return new ResponseEntity<>(result, HttpStatus.CREATED);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{productId}/metas/{metaId}")
  public ResponseEntity<ProductMeta> updateProductMeta(
      @PathVariable(name = "productId") Long productId,
      @PathVariable(name = "metaId") Long metaId,
      @RequestBody ProductMeta meta) {
    try {
      ProductMeta result = productMetaService.updateProductMeta(productId, metaId, meta);
      if(result == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{productId}/metas/{metaId}")
  public ResponseEntity<?> deleteProductMetaById(
      @PathVariable(name = "productId") Long productId,
      @PathVariable(name = "metaId") Long metaId) {
    try {
      boolean result = productMetaService.deleteProductMetaById(productId, metaId);
      if (result)
        return new ResponseEntity<>("Delete meta id: " + metaId + " success", HttpStatus.OK);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
