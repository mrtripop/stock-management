package com.learn.main.address;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/address")
@Slf4j
public class AddressController {

  private final AddressService addressService;

  @Autowired
  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  @GetMapping
  public ResponseEntity<List<Address>> getUserAddress(
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "10") Integer size,
      @RequestParam(defaultValue = "asc") String orderBy) {
    try {
      List<Address> result = addressService.retrieveUserAddress(page, size, orderBy);
      log.debug(result.toString());
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      log.error(e.getMessage(), e.getMessage());
      return ResponseEntity.status(500).body(null);
    }
  }

  @PostMapping
  public ResponseEntity<Address> createUserAddress(@RequestBody Address address) {
    try {
      Address result = addressService.createNewUserAddress(address);
      log.debug(result.toString());
      return ResponseEntity.ok(result);
    } catch (Exception e) {
      log.error(e.getMessage(), e.getCause());
      return ResponseEntity.status(500).body(null);
    }
  }
}
