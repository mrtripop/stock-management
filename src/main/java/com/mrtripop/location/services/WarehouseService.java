package com.mrtripop.location.services;

import com.mrtripop.exception.GlobalThrowable;
import com.mrtripop.location.constant.ErrorCode;
import com.mrtripop.location.models.entities.Address;
import com.mrtripop.location.models.entities.Warehouse;
import com.mrtripop.location.repositories.WarehouseRepository;
import com.mrtripop.model.QueryParams;
import com.mrtripop.util.DatabaseHelper;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WarehouseService {

  private final WarehouseRepository warehouseRepository;
  private final AddressService addressService;

  public WarehouseService(WarehouseRepository warehouseRepository, AddressService addressService) {
    this.warehouseRepository = warehouseRepository;
    this.addressService = addressService;
  }

  // Features
  // - getWarehouses -> admin
  // - getWarehouseByWarehouseID -> admin, manager, operator
  // - getWarehousesByAddressID -> admin, manager
  // - addWarehouse
  public List<Warehouse> getWarehouses(QueryParams queryParams) throws GlobalThrowable {
    try {
      Pageable pageable = DatabaseHelper.initPageableWithSort(queryParams);
      Page<Warehouse> warehousePages = warehouseRepository.findAll(pageable);
      return warehousePages.getContent();
    } catch (Exception e) {
      log.error("Cannot select all warehouse: {}", e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.UAD5001_CANNOT_RETRIEVE_ADDRESSES, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public Warehouse getWarehouseByWarehouseId(Long warehouseId) throws GlobalThrowable {
    try {
      Optional<Warehouse> existingWarehouse = warehouseRepository.findById(warehouseId);
      return existingWarehouse.orElseThrow(
          () ->
              new GlobalThrowable(
                  ErrorCode.UAD5001_CANNOT_RETRIEVE_ADDRESSES, HttpStatus.NOT_FOUND));
    } catch (Exception e) {
      log.error("Cannot select warehouse by warehouse Id({}): {}", warehouseId, e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.UAD5001_CANNOT_RETRIEVE_ADDRESSES, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public Warehouse getWarehouseByAddressId(Long addressId) throws GlobalThrowable {
    try {
      Address address = addressService.getAddressesById(addressId);
      Optional<Warehouse> existingWarehouse = warehouseRepository.findByAddress(address);
      return existingWarehouse.orElseThrow(
          () ->
              new GlobalThrowable(
                  ErrorCode.UAD5001_CANNOT_RETRIEVE_ADDRESSES, HttpStatus.NOT_FOUND));
    } catch (Exception e) {
      log.error("Cannot select warehouse by address Id({}): {}", addressId, e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.UAD5001_CANNOT_RETRIEVE_ADDRESSES, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
