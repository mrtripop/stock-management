package com.mrtripop.inventory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel {

  private String code;
  private String message;
  private Object data;
  private Object error;

  public ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus) {
    return new ResponseEntity<>(this, httpStatus);
  }
}
