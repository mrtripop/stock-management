package com.mrtripop.location.components;

import com.mrtripop.product.models.db.ProductHistory;
import org.springframework.data.jpa.domain.Specification;

public class WarehouseSpecification {

  private WarehouseSpecification() {}

  public static Specification<ProductHistory> productsHaveCode(String code) {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.equal(root.get("code").as(String.class), code);
  }
}
