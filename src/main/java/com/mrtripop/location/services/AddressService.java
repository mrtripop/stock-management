package com.mrtripop.location.services;

import com.mrtripop.exception.GlobalThrowable;
import com.mrtripop.location.constant.ErrorCode;
import com.mrtripop.location.models.dtos.AddressDTO;
import com.mrtripop.location.models.entities.Address;
import com.mrtripop.location.repositories.AddressRepository;
import com.mrtripop.location.utils.AddressUtil;
import com.mrtripop.model.QueryParams;
import com.mrtripop.util.DatabaseHelper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AddressService {

  private final AddressRepository addressRepository;

  public AddressService(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  public List<Address> getAddresses(QueryParams queryParams) throws GlobalThrowable {
    try {
      Pageable pageable = DatabaseHelper.initPageableWithSort(queryParams);
      Page<Address> pagesAddress = addressRepository.findAll(pageable);
      return pagesAddress.getContent();
    } catch (Exception e) {
      log.error("Cannot select addresses: {}", e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.UAD5001_CANNOT_RETRIEVE_ADDRESSES, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public Address getAddressesById(Long addressId) throws GlobalThrowable {
    try {
      return addressRepository
          .findById(addressId)
          .orElseThrow(
              () ->
                  new GlobalThrowable(
                      ErrorCode.UAD5003_NOT_FOUND_ADDRESSES_FOR_ADDRESS_ID, HttpStatus.NOT_FOUND));
    } catch (Exception e) {
      log.error("Cannot select addresses by address ID({}): {}", addressId, e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.UAD5002_CANNOT_RETRIEVE_ADDRESSES_BY_ADDRESS_ID,
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public Address addNewAddress(AddressDTO addressDTO) throws GlobalThrowable {
    try {
      Address address = AddressUtil.dtoToAddress(addressDTO);
      return addressRepository.save(address);
    } catch (Exception e) {
      log.error("Cannot insert new address: {}", e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.UAD5001_CANNOT_RETRIEVE_ADDRESSES, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
