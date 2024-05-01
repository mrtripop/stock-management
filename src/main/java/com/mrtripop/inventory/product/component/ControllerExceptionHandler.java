package com.mrtripop.inventory.product.component;

import com.mrtripop.inventory.constant.BaseStatusCode;
import com.mrtripop.inventory.exception.GlobalThrowable;
import com.mrtripop.inventory.model.ResponseBody;
import com.mrtripop.inventory.product.constant.ErrorCode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(annotations = RestController.class)
public class ControllerExceptionHandler {

  @ExceptionHandler(GlobalThrowable.class)
  public ResponseEntity<Object> handleGlobalThrowable(GlobalThrowable ex) {
    BaseStatusCode errorCode = ex.getErrorCode();
    log.error("Error code: {}, message: {}", errorCode.getCode(), errorCode.getMessage());
    return ResponseBody.builder()
        .code(errorCode.getCode())
        .message(errorCode.getMessage())
        .build()
        .buildResponseEntity(ex.getHttpStatus());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGlobalException(Exception ex) {
    log.error("Global error: {}", ex.getMessage());
    return ResponseBody.builder()
        .code("APP5000")
        .message("Application is failed")
        .build()
        .buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    List<String> errors =
        ex.getBindingResult().getFieldErrors().stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.toList());
    BaseStatusCode errorCode = ErrorCode.PRO1006_PRODUCT_REQUEST_BODY_IS_NOT_VALID;
    return ResponseBody.builder()
        .code(errorCode.getCode())
        .message(errorCode.getMessage())
        .error(getArgumentNotValidErrorMessage(errors))
        .build()
        .buildResponseEntity(HttpStatus.BAD_REQUEST);
  }

  private Map<String, List<String>> getArgumentNotValidErrorMessage(List<String> errors) {
    Map<String, List<String>> errorResponse = new HashMap<>();
    errorResponse.put("error_message", errors);
    return errorResponse;
  }
}
