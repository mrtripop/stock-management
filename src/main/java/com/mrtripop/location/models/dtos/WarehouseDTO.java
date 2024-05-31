package com.mrtripop.location.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDTO {
  private Long warehouseId;
  private String warehouseName;
  private Boolean isRefrigerated;
  private Long addressId;
}
