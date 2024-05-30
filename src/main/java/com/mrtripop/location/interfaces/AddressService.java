package com.mrtripop.location.interfaces;

import com.mrtripop.exception.GlobalThrowable;
import com.mrtripop.location.models.dtos.AddressDTO;
import com.mrtripop.model.QueryParams;
import java.util.List;

public interface AddressService {
  List<AddressDTO> getAllAddress(QueryParams queryParams) throws GlobalThrowable;

  AddressDTO getAddressById(Long addressId) throws GlobalThrowable;

  AddressDTO addNewAddress(AddressDTO newAddress) throws GlobalThrowable;

  AddressDTO updateAddress(Long addressId, AddressDTO newAddress) throws GlobalThrowable;

  void deleteAddressById(Long addressId) throws GlobalThrowable;
}
