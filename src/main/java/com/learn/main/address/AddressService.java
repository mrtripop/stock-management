package com.learn.main.address;

import com.learn.helper.DatabaseHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AddressService {

  private final AddressRepository addressRepository;

  @Autowired
  public AddressService(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  public List<Address> retrieveUserAddress(Integer page, Integer size, String orderBy) {
    try {
      Pageable pageSize = DatabaseHelper.initPageableWithSort(page, size, orderBy);
      Page<Address> pageAddress = addressRepository.findAll(pageSize);
      log.debug(pageAddress.toString());
      return this.addressRepository.findAll();
    } catch (Exception e) {
      log.error(e.toString());
      throw new RuntimeException("GetUserAddressException", e.getCause());
    }
  }

  public Address createNewUserAddress(Address address) {
    try {
      return this.addressRepository.save(address);
    } catch (Exception e) {
      throw new RuntimeException("CreateNewUserAddressException", e.getCause());
    }
  }
}
