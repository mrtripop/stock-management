package com.mrtripop.location.utils;

import com.mrtripop.location.models.dtos.WarehouseDTO;
import com.mrtripop.location.models.entities.Warehouse;

public class WarehouseUtil {
  private WarehouseUtil() {}

  public static WarehouseDTO warehouseToDTO(Warehouse warehouse) {
    return WarehouseDTO.builder()
        .warehouseId(warehouse.getId())
        .warehouseName(warehouse.getWarehouseName())
        .isRefrigerated(warehouse.getIsRefrigerated())
        .addressId(warehouse.getAddress().getId())
        .build();
  }

  public static Warehouse dtoToWarehouse(WarehouseDTO warehouse) {
    return Warehouse.builder()
        .id(warehouse.getWarehouseId())
        .warehouseName(warehouse.getWarehouseName())
        .isRefrigerated(warehouse.getIsRefrigerated())
        .build();
  }

  public static void updateWarehouse(Warehouse oldWarehouse, Warehouse newWarehouse) {
    oldWarehouse.setWarehouseName(newWarehouse.getWarehouseName());
    oldWarehouse.setIsRefrigerated(newWarehouse.getIsRefrigerated());
    oldWarehouse.setAddress(newWarehouse.getAddress());
  }
}
