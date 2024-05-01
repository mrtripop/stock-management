package com.mrtripop.inventory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBody {
  private String code;
  private String message;
  private Object data;
  private Object error;

  public HttpEntity<ResponseBody> buildResponseEntity(HttpStatus httpStatus) {
    return new ResponseEntity<>(new ResponseBody(code, message, data, error), httpStatus);
  }
}
