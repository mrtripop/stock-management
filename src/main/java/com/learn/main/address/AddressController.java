package com.learn.main.address;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@Slf4j
public class AddressController {

  private final AddressService addressService;

  @Autowired
  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  @GetMapping("/user/{userId}/address")
  public ResponseEntity<List<Address>> getUserAddress(
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "10") Integer size,
      @RequestParam(defaultValue = "asc") String orderBy,
      @PathVariable(name = "userId") Long userId) {
    try {
      List<Address> result = addressService.retrieveUserAddress(userId, page, size, orderBy);
      log.debug(result.toString());
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      log.error("Get user address: " + error);
      return ResponseEntity.status(500).body(null);
    }
  }

  @GetMapping("/user/{userId}/address/{addressId}")
  public ResponseEntity<Address> getUserAddressById(
      @PathVariable Long userId, @PathVariable Long addressId) {
    try {
      Address userAddress = addressService.retrieveUserAddressById(userId, addressId);
      if (userAddress != null) {
        return new ResponseEntity<>(userAddress, HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      log.error("Get user address by id: " + error);
      return ResponseEntity.status(500).body(null);
    }
  }

  @PostMapping("/user/{userId}/address")
  public ResponseEntity<Address> createUserAddress(
      @PathVariable(name = "userId") Long userId, @RequestBody Address address) {
    try {
      Address result = addressService.createNewUserAddress(userId, address);
      if (result != null) {
        log.debug(result.toString());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      log.error("Create user address: " + error);
      return ResponseEntity.status(500).body(null);
    }
  }

  @DeleteMapping("/user/{userId}/address/{addressId}")
  public ResponseEntity<?> deleteUserAddress(
      @PathVariable(name = "userId") Long userId,
      @PathVariable(name = "addressId") Long addressId) {
    try {
      boolean result = addressService.deleteUserAddressById(userId, addressId);
      if (result) {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      log.error("Delete user address: " + error);
      return ResponseEntity.status(500).body(null);
    }
  }
}
