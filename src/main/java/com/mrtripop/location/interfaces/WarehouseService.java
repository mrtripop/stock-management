package com.mrtripop.location.interfaces;

import com.mrtripop.exception.GlobalThrowable;
import com.mrtripop.location.models.dtos.WarehouseDTO;
import com.mrtripop.location.models.entities.Warehouse;
import com.mrtripop.model.QueryParams;
import java.util.List;

public interface WarehouseService {

  List<Warehouse> getAllWarehouse(QueryParams queryParams) throws GlobalThrowable;

  Warehouse getWarehouseById(Long warehouseId) throws GlobalThrowable;

  Warehouse addNewWarehouse(WarehouseDTO newWarehouse) throws GlobalThrowable;

  Warehouse updateWarehouse(Long warehouseId, WarehouseDTO newWarehouse) throws GlobalThrowable;

  void deleteWarehouse(Long warehouseId) throws GlobalThrowable;
}
