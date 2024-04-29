package com.mrtripop.inventory.product.component;

import com.mrtripop.inventory.constant.BaseStatusCode;
import com.mrtripop.inventory.exception.GlobalThrowable;
import com.mrtripop.inventory.model.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(value = {GlobalThrowable.class})
  public ResponseEntity<Object> handleGlobalThrowable(GlobalThrowable ex) {
    log.error("Global throwable exception: {}", ex.getErrorCode().getMessage());
    BaseStatusCode errorCode = ex.getErrorCode();
    return ResponseBody.builder()
        .code(errorCode.getCode())
        .message(errorCode.getMessage())
        .build()
        .buildResponseEntity(ex.getHttpStatus());
  }
}
