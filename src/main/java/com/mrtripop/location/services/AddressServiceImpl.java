package com.mrtripop.location.services;

import com.mrtripop.exception.GlobalThrowable;
import com.mrtripop.location.constant.ErrorCode;
import com.mrtripop.location.interfaces.AddressService;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

  private final AddressRepository addressRepository;

  public AddressServiceImpl(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  @Override
  public List<AddressDTO> getAllAddress(QueryParams queryParams) throws GlobalThrowable {
    try {
      List<Address> addresses = retrieveAllAddress(queryParams);
      return addresses.stream().map(AddressUtil::addressToDTO).toList();
    } catch (Exception e) {
      log.error("Cannot select addresses: {}", e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.UAD5001_CANNOT_RETRIEVE_ADDRESSES, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public AddressDTO getAddressById(Long addressId) throws GlobalThrowable {
    try {
      Address address = findAddressById(addressId);
      return AddressUtil.addressToDTO(address);
    } catch (Exception e) {
      log.error("Cannot select addresses by address ID({}): {}", addressId, e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.UAD5002_CANNOT_RETRIEVE_ADDRESSES_BY_ADDRESS_ID,
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public AddressDTO addNewAddress(AddressDTO newAddress) throws GlobalThrowable {
    try {
      Address address = AddressUtil.dtoToAddress(newAddress);
      Address createdAddress = addressRepository.save(address);
      return AddressUtil.addressToDTO(createdAddress);
    } catch (Exception e) {
      log.error("Cannot insert new address: {}", e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.UAD5001_CANNOT_RETRIEVE_ADDRESSES, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public AddressDTO updateAddress(Long addressId, AddressDTO newAddress) throws GlobalThrowable {
    try {
      Address existingAddress = findAddressById(addressId);
      AddressUtil.updateAddress(existingAddress, newAddress);
      Address updatedAddress = addressRepository.save(existingAddress);
      return AddressUtil.addressToDTO(updatedAddress);
    } catch (Exception e) {
      log.error("Cannot update for address ID '{}': {}", addressId, e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.UAD5005_CANNOT_UPDATE_THE_EXISTING_ADDRESS_ID,
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public void deleteAddressById(Long addressId) throws GlobalThrowable {
    try {
      Address existingAddress = findAddressById(addressId);
      addressRepository.delete(existingAddress);
    } catch (Exception e) {
      log.error("Cannot delete the address ID '{}': {}", addressId, e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.UAD5007_CANNOT_DELETE_THE_ADDRESS_ID, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public List<Address> retrieveAllAddress(QueryParams queryParams) {
    Pageable pageable = DatabaseHelper.initPageableWithSort(queryParams);
    Page<Address> pagesAddress = addressRepository.findAll(pageable);
    return pagesAddress.getContent();
  }

  public Address findAddressById(Long addressId) throws GlobalThrowable {
    return addressRepository
        .findById(addressId)
        .orElseThrow(
            () ->
                new GlobalThrowable(
                    ErrorCode.UAD5003_NOT_FOUND_ADDRESSES_FOR_ADDRESS_ID, HttpStatus.NOT_FOUND));
  }
}
