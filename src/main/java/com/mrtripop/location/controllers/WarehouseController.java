package com.mrtripop.location.controllers;

import com.mrtripop.constant.BaseStatusCode;
import com.mrtripop.exception.GlobalThrowable;
import com.mrtripop.location.constant.ErrorCode;
import com.mrtripop.location.constant.SuccessCode;
import com.mrtripop.location.models.dtos.WarehouseDTO;
import com.mrtripop.location.models.entities.Warehouse;
import com.mrtripop.location.services.WarehouseServiceImpl;
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

  private final WarehouseServiceImpl warehouseService;

  public WarehouseController(WarehouseServiceImpl warehouseService) {
    this.warehouseService = warehouseService;
  }

  @GetMapping
  public ResponseEntity<Object> getWarehouses(@Valid QueryParams queryParams)
      throws GlobalThrowable {
    try {
      List<Warehouse> warehouses = warehouseService.getAllWarehouse(queryParams);
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
      Warehouse warehouse = warehouseService.getWarehouseById(warehouseId);
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

  @PostMapping
  public ResponseEntity<Object> addNewWarehouse(@RequestBody WarehouseDTO newWarehouse)
      throws GlobalThrowable {
    try {
      Warehouse warehouse = warehouseService.addNewWarehouse(newWarehouse);
      BaseStatusCode status = SuccessCode.SUCCESS;
      return ResponseBody.builder()
          .code(status.getCode())
          .message(status.getMessage())
          .data(warehouse)
          .build()
          .buildResponseEntity(HttpStatus.OK);
    } catch (Exception e) {
      log.error("Cannot add a new warehouse: {}", e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.UAD5001_CANNOT_RETRIEVE_ADDRESSES, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
