package com.mrtripop.inventory.exception;

import com.mrtripop.inventory.constant.BaseStatusCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GlobalThrowable extends Throwable {

  private final BaseStatusCode errorCode;
  private final HttpStatus httpStatus;

  public GlobalThrowable(BaseStatusCode errorCode, HttpStatus httpStatus) {
    this.errorCode = errorCode;
    this.httpStatus = httpStatus;
  }
}
