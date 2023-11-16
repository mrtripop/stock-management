package com.mrtripop.inventory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel {

  @JsonProperty private StatusModel status;

  @JsonProperty private Object data;

  public ResponseModel(StatusModel status) {
    this.status = status;
  }

  public ResponseModel(StatusModel status, Object data) {
    this.status = status;
    this.data = data;
  }

  public ResponseEntity<Object> build(HttpStatus httpStatus) {
    return new ResponseEntity<>(new ResponseModel(this.status, this.data), httpStatus);
  }
}
