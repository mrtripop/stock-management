package com.mrtripop.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Sort;

public class QueryParams {

  @Min(value = 1, message = "Input page must not less than one")
  @NotNull(message = "Input page must not be null")
  private Integer page = 1;

  @Min(value = 1, message = "Input page size must not less than one")
  @NotNull(message = "Input page size must not be null")
  private Integer size = 200;

  @NotNull(message = "Order by query param must not be null")
  private Sort.Direction orderBy = Sort.Direction.ASC;

  public Integer getPage() {
    return page;
  }

  public void setPage(String page) {
    this.page = Integer.valueOf(page);
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = Integer.valueOf(size);
  }

  public Sort.Direction getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = Sort.Direction.fromString(orderBy);
  }
}
