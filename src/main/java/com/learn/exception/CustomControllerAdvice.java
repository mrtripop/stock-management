package com.learn.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomControllerAdvice {

  // exception handled
  @ExceptionHandler(NullPointerException.class)
  public ResponseEntity<ErrorResponse> handleNullPointerExceptions(Exception e) {
    String stackTrace = ExceptionUtils.getStackTrace(e);
    log.error(stackTrace);
    HttpStatus status = HttpStatus.NOT_FOUND; // 404
    ErrorResponse error =
        new ErrorResponse.ErrorResponseBuilder<>(status.toString(), status.value(), e.getMessage())
            .withStacktrace(stackTrace)
            .withTimestamp()
            .build();
    return new ResponseEntity<>(error, status);
  }

  // fallback method
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleExceptions(Exception e) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500
    // converting the stack trace to String
    String stackTrace = ExceptionUtils.getStackTrace(e);
    log.error(stackTrace);
    ErrorResponse error =
        new ErrorResponse.ErrorResponseBuilder<>(
                status.toString(), status.value(), status.getReasonPhrase())
            .withStacktrace(stackTrace)
            .withTimestamp()
            .build();
    return new ResponseEntity<>(error, status);
  }
}
