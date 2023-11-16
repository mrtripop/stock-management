package com.mrtripop.inventory.service;

import com.mrtripop.inventory.util.DatabaseHelper;
import com.mrtripop.inventory.model.User;
import com.mrtripop.inventory.repository.UserRepository;
import com.mrtripop.inventory.model.Address;
import com.mrtripop.inventory.repository.AddressRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AddressService {

  private final AddressRepository addressRepository;
  private final UserRepository userRepository;

  @Autowired
  public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
    this.addressRepository = addressRepository;
    this.userRepository = userRepository;
  }

  public List<Address> retrieveUserAddress(
      Long userId, Integer page, Integer size, String orderBy) {
    try {
      Optional<User> user = userRepository.findById(userId);
      if (user.isPresent()) {
        Pageable pageSize = DatabaseHelper.initPageableWithSort(page, size, orderBy);
        Page<Address> pageAddress = addressRepository.findByUserId(userId, pageSize);
        log.debug(pageAddress.toString());
        return pageAddress.stream().toList();
      }
      return List.of();
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      throw new RuntimeException(error, e.getCause());
    }
  }

  public Address retrieveUserAddressById(Long userId, Long addressId) {
    try {
      Optional<User> user = userRepository.findById(userId);
      if (user.isPresent()) {
        Optional<Address> userAddress = addressRepository.findByUserIdAndId(userId, addressId);
        return userAddress.orElse(null);
      }
      return null;
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      throw new RuntimeException(error, e.getCause());
    }
  }

  public Address createNewUserAddress(Long userId, Address address) {
    try {
      Optional<User> user = userRepository.findById(userId);
      if (user.isPresent()) {
        log.info(user.get().toString());
        address.setUser(user.get());
        address.setCreatedAt(ZonedDateTime.now());
        address.setUpdatedAt(ZonedDateTime.now());
        return addressRepository.save(address);
      }
      return null;
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      throw new RuntimeException(error, e.getCause());
    }
  }

  @Transactional
  public boolean deleteUserAddressById(Long userId, Long addressId) {
    try {
      Optional<User> user = userRepository.findById(userId);
      if (user.isPresent()) {
        addressRepository.deleteByUserIdAndId(userId, addressId);
        return true;
      }
      return false;
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      throw new RuntimeException(error, e.getCause());
    }
  }
}
