package com.mrtripop.inventory.model;

import lombok.Getter;

@Getter
public class StatusModel {

  private final String code;
  private final String message;

  public StatusModel(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
