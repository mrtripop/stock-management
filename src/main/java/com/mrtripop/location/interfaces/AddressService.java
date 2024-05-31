package com.mrtripop.location.interfaces;

import com.mrtripop.exception.GlobalThrowable;
import com.mrtripop.location.models.dtos.AddressDTO;
import com.mrtripop.location.models.entities.Address;
import com.mrtripop.model.QueryParams;
import java.util.List;

public interface AddressService {
  List<Address> getAllAddress(QueryParams queryParams) throws GlobalThrowable;

  Address getAddressById(Long addressId) throws GlobalThrowable;

  Address addNewAddress(AddressDTO newAddress) throws GlobalThrowable;

  Address updateAddress(Long addressId, AddressDTO newAddress) throws GlobalThrowable;

  void deleteAddressById(Long addressId) throws GlobalThrowable;
}
