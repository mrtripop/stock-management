package com.mrtripop.inventory.product.util;

import com.mrtripop.inventory.product.models.ProductHistory;
import org.springframework.data.jpa.domain.Specification;

public class ProductUtil {

  private ProductUtil() {}

  public static Specification<ProductHistory> productsHaveCode(String code) {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.equal(root.get("code").as(String.class), code);
  }
}
