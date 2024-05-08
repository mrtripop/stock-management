package com.mrtripop.inventory.product.constant;

import lombok.Getter;

@Getter
public enum OrderBy {
  ASC("ASC"),
  DESC("DESC");

  private final String content;

  OrderBy(String content) {
    this.content = content;
  }
}
