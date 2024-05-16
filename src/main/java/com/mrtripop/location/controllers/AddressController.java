package com.mrtripop.location.controllers;

import com.mrtripop.location.constant.ErrorCode;
import com.mrtripop.location.constant.SuccessCode;
import com.mrtripop.location.models.Address;
import com.mrtripop.location.services.AddressService;
import com.mrtripop.constant.BaseStatusCode;
import com.mrtripop.exception.GlobalThrowable;
import com.mrtripop.model.ResponseBody;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/inventory/addresses")
public class AddressController {

  private final AddressService addressService;

  @Autowired
  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  // admin permission
  @GetMapping
  public ResponseEntity<Object> getAddresses(
      @RequestParam(defaultValue = "1") @Min(value = 1, message = "Page must not less than one")
          Integer page,
      @RequestParam(defaultValue = "200") @Min(value = 1, message = "Size must not less than one")
          Integer size,
      @RequestParam(defaultValue = "ASC") Sort.Direction orderBy)
      throws GlobalThrowable {
    try {
      List<Address> addresses = addressService.getAddresses(page, size, orderBy);
      BaseStatusCode code = SuccessCode.SUCCESS;
      return ResponseBody.builder()
          .code(code.getCode())
          .message(code.getMessage())
          .data(addresses)
          .build()
          .buildResponseEntity(HttpStatus.OK);
    } catch (Exception e) {
      log.error("Cannot get user address: {}", e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.UAD5001_CANNOT_RETRIEVE_ADDRESSES, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // admin permission
  @GetMapping("/{addressId}")
  public ResponseEntity<Object> getAddressesById(
      @PathVariable @Min(value = 1, message = "Address ID is invalid") Long addressId)
      throws GlobalThrowable {
    try {
      Address address = addressService.getAddressesById(addressId);
      BaseStatusCode code = SuccessCode.SUCCESS;
      return ResponseBody.builder()
          .code(code.getCode())
          .message(code.getMessage())
          .data(address)
          .build()
          .buildResponseEntity(HttpStatus.OK);
    } catch (Exception e) {
      log.error("Cannot get addresses by ID '{}': {}", addressId, e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.UAD5003_NOT_FOUND_ADDRESSES_FOR_ADDRESS_ID, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
