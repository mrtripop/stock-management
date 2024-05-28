package com.mrtripop.location.controllers;

import com.mrtripop.constant.BaseStatusCode;
import com.mrtripop.exception.GlobalThrowable;
import com.mrtripop.location.constant.ErrorCode;
import com.mrtripop.location.constant.SuccessCode;
import com.mrtripop.location.models.entities.Warehouse;
import com.mrtripop.location.services.WarehouseService;
import com.mrtripop.model.QueryParams;
import com.mrtripop.model.ResponseBody;
import jakarta.validation.Valid;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/inventory/warehouses")
public class WarehouseController {

  private final WarehouseService warehouseService;

  public WarehouseController(WarehouseService warehouseService) {
    this.warehouseService = warehouseService;
  }

  @GetMapping
  public ResponseEntity<Object> getWarehouses(@Valid QueryParams queryParams)
      throws GlobalThrowable {
    try {
      List<Warehouse> warehouses = warehouseService.getWarehouses(queryParams);
      BaseStatusCode status = SuccessCode.SUCCESS;
      return ResponseBody.builder()
          .code(status.getCode())
          .message(status.getMessage())
          .data(warehouses)
          .build()
          .buildResponseEntity(HttpStatus.OK);
    } catch (Exception e) {
      log.error("Cannot get all warehouse: {}", e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.UAD5001_CANNOT_RETRIEVE_ADDRESSES, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{warehouse_id}")
  public ResponseEntity<Object> getWarehouseByWarehouseId(
      @PathVariable(name = "warehouse_id") Long warehouseId) throws GlobalThrowable {
    try {
      Warehouse warehouse = warehouseService.getWarehouseByWarehouseId(warehouseId);
      BaseStatusCode status = SuccessCode.SUCCESS;
      return ResponseBody.builder()
          .code(status.getCode())
          .message(status.getMessage())
          .data(warehouse)
          .build()
          .buildResponseEntity(HttpStatus.OK);
    } catch (Exception e) {
      log.error("Cannot get warehouse by warehouse Id({}): {}", warehouseId, e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.UAD5001_CANNOT_RETRIEVE_ADDRESSES, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/addresses/{address_id}")
  public ResponseEntity<Object> getWarehouseByAddressId(
      @PathVariable(name = "address_id") Long addressId) throws GlobalThrowable {
    try {
      Warehouse warehouse = warehouseService.getWarehouseByAddressId(addressId);
      BaseStatusCode status = SuccessCode.SUCCESS;
      return ResponseBody.builder()
          .code(status.getCode())
          .message(status.getMessage())
          .data(warehouse)
          .build()
          .buildResponseEntity(HttpStatus.OK);
    } catch (Exception e) {
      log.error("Cannot get warehouse by address Id({}): {}", addressId, e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.UAD5001_CANNOT_RETRIEVE_ADDRESSES, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
