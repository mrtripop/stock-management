package com.mrtripop.inventory.product.constant;

import com.mrtripop.inventory.constant.BaseStatusCode;

public enum ErrorCode implements BaseStatusCode {
  PRO1001_CANNOT_GET_ALL_PRODUCTS("PRO1001", "Cannot get all products"),
  PRO1002_CANNOT_CREATE_NEW_PRODUCT("PRO1002", "Cannot create new product"),
  PRO1003_CANNOT_GET_PRODUCT_BY_ID("PRO1003", "Cannot get product by ID"),
  PRO1004_CANNOT_UPDATE_EXISTING_PRODUCT("PRO1004", "Cannot update existing product"),
  PRO1005_CANNOT_DELETE_EXISTING_PRODUCT("PRO1005", "Cannot delete the product"),
  PRO1006_PRODUCT_REQUEST_BODY_IS_NOT_VALID("PRO1006", "Product request body is not valid");
  private final String code;
  private final String message;

  ErrorCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

  @Override
  public String getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
