package com.mrtripop.constant;

import lombok.Getter;

@Getter
public enum SuccessCode {
  USER2000_RETRIEVE_USERS_SUCCESS("USER2000", "Retrieve all users from database success");
  private final String code;
  private final String message;

  SuccessCode(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
