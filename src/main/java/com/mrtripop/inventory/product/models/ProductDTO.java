package com.mrtripop.inventory.product.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
  private Long id;
  private String code;
  private String barcode;
  private String name;
  private String description;
  private String category;
  private Integer reorderQuantity;
  private Double packedWeight;
  private Double packedHeight;
  private Double packedWidth;
  private Double packedDepth;
  private Boolean isActive;
}
