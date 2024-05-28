package com.mrtripop.location.utils;

import com.mrtripop.location.models.dtos.AddressDTO;
import com.mrtripop.location.models.entities.Address;

public class AddressUtil {
  private AddressUtil() {}

  public static Address dtoToAddress(AddressDTO addressDTO) {
    return Address.builder()
        .addressName(addressDTO.getAddressName())
        .line1(addressDTO.getLine1())
        .line2(addressDTO.getLine2())
        .city(addressDTO.getCity())
        .province(addressDTO.getProvince())
        .country(addressDTO.getCountry())
        .postalCode(addressDTO.getPostalCode())
        .warehouses(addressDTO.getWarehouses())
        .build();
  }
}
