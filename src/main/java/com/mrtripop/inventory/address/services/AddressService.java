package com.mrtripop.inventory.address.services;

import com.mrtripop.inventory.address.constant.ErrorCode;
import com.mrtripop.inventory.address.models.Address;
import com.mrtripop.inventory.address.repositories.AddressRepository;
import com.mrtripop.inventory.exception.GlobalThrowable;
import com.mrtripop.inventory.util.DatabaseHelper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AddressService {

  private final AddressRepository addressRepository;

  @Autowired
  public AddressService(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  public List<Address> getAddresses(Integer page, Integer size, Sort.Direction orderBy)
      throws GlobalThrowable {
    try {
      Pageable pageable = DatabaseHelper.initPageableWithSort(page, size, orderBy);
      Page<Address> pagesAddress = addressRepository.findAll(pageable);
      return pagesAddress.getContent();
    } catch (Exception e) {
      log.error("Cannot retrieve addresses: {}", e.getMessage());
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
      log.error("Cannot get addresses by address ID '{}': {}", addressId, e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.UAD5002_CANNOT_RETRIEVE_ADDRESSES_BY_ADDRESS_ID,
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
