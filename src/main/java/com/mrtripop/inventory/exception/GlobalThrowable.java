package com.mrtripop.inventory.exception;

import com.mrtripop.inventory.constant.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GlobalThrowable extends Throwable {

  private final ErrorCode errorCode;
  private final HttpStatus httpStatus;

  public GlobalThrowable(ErrorCode errorCode, HttpStatus httpStatus) {
    this.errorCode = errorCode;
    this.httpStatus = httpStatus;
  }
}
