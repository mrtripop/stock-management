package com.mrtripop.inventory.util;

import com.mrtripop.inventory.model.User;
import org.springframework.data.jpa.domain.Specification;

import java.time.ZonedDateTime;

public class UserSpecificationHelper {
  public static Specification<User> lastLogin(ZonedDateTime start, ZonedDateTime end) {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.between(root.get("last_login"), start, start);
  }

  public static Specification<User> todayRegister(ZonedDateTime today) {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.equal(root.get("registered_at"), today);
  }
}
