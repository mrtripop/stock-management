package com.mrtripop.location.models.dtos;

import com.mrtripop.location.models.entities.Warehouse;
import java.util.List;
import lombok.Data;

@Data
public class AddressDTO {
  private String addressName;
  private String line1;
  private String line2;
  private String city;
  private String province;
  private String country;
  private String postalCode;
  private List<Warehouse> warehouses;
}
