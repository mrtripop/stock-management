package com.learn.repository;

import com.learn.model.ProductMeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMetaRepository extends JpaRepository<ProductMeta, Long> {
}
