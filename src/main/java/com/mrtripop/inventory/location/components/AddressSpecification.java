package com.mrtripop.inventory.location.components;

import com.mrtripop.inventory.product.models.db.ProductHistory;
import org.springframework.data.jpa.domain.Specification;

public class AddressSpecification {

    private AddressSpecification(){}

    public static Specification<ProductHistory> productsHaveCode(String code) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("code").as(String.class), code);
    }
}
