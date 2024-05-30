package com.mrtripop.location.constant;

import com.mrtripop.constant.BaseStatusCode;

public enum ErrorCode implements BaseStatusCode {
  UAD5001_CANNOT_RETRIEVE_ADDRESSES("UAD5001", "Cannot retrieve addresses"),
  UAD5002_CANNOT_RETRIEVE_ADDRESSES_BY_ADDRESS_ID(
      "UAD5002", "Cannot retrieve addresses by address ID"),
  UAD5003_NOT_FOUND_ADDRESSES_FOR_ADDRESS_ID("UAD5003", "Not found addresses for the address ID"),
  UAD5004_CANNOT_UPDATE_THE_ADDRESS("UAD5004", "The controller cannot update the address"),
  UAD5005_CANNOT_UPDATE_THE_EXISTING_ADDRESS_ID("UAD5005", "Cannot update the existing address ID"),
  UAD5006_CANNOT_DELETE_THE_ADDRESS("UAD5006", "Cannot delete the address"),
  UAD5007_CANNOT_DELETE_THE_ADDRESS_ID("UAD5007", "Cannot delete the address ID");

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
