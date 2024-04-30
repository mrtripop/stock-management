package com.mrtripop.inventory.product.constant;

import com.mrtripop.inventory.constant.BaseStatusCode;

public enum SuccessCode implements BaseStatusCode {
  PRO2001_GET_ALL_PRODUCTS_IS_SUCCESS("PRO2001", "Get all products is success"),
  PRO2002_GET_PRODUCTS_BY_ID_IS_SUCCESS("PRO2002", "Get product by ID is success"),
  PRO2003_CREATE_NEW_PRODUCT_IS_SUCCESS("PRO2003", "Create new product is success"),
  PRO2004_UPDATE_PRODUCT_IS_SUCCESS("PRO2004", "Update the product is success"),
  PRO2005_DELETE_PRODUCT_IS_SUCCESS("PRO2005", "Delete product is success"),
  PRO2006_GET_ALL_PRODUCT_HISTORIES_IS_SUCCESS("PRO2006", "Get all product histories is success");
  private final String code;
  private final String message;

  SuccessCode(String code, String message) {
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
