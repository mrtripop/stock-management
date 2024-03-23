package com.mrtripop.inventory.constant;

import lombok.Getter;

@Getter
public enum ErrorCode {
  CANNOT_GET_ALL_SONG_BY_INTERRUPT_EXCEPTION("SBI4001", "Cannot get song by interrupt exception"),
  CANNOT_GET_ALL_SONG_EXCEPTION("SBE4002", "Cannot get song exception");

  private final String code;
  private final String message;

  ErrorCode(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
