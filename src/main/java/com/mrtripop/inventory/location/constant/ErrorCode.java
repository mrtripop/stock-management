package com.mrtripop.inventory.location.constant;

import com.mrtripop.inventory.constant.BaseStatusCode;

public enum ErrorCode implements BaseStatusCode {
  UAD5001_CANNOT_RETRIEVE_ADDRESSES("UAD5001", "Cannot retrieve addresses"),
  UAD5002_CANNOT_RETRIEVE_ADDRESSES_BY_ADDRESS_ID(
      "UAD5002", "Cannot retrieve addresses by address ID"),
  UAD5003_NOT_FOUND_ADDRESSES_FOR_ADDRESS_ID("UAD5003", "Not found addresses for the address ID");

  private final String code;
  private final String message;

  ErrorCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

  @Override
  public String getCode() {
    return this.code;
  }

  @Override
  public String getMessage() {
    return this.message;
  }
}
