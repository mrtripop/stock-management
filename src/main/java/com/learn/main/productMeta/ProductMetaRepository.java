package com.learn.main.productMeta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductMetaRepository extends JpaRepository<ProductMeta, Long> {
  Optional<ProductMeta> findByProductIdAndId(Long productId, Long metaId);

  Page<ProductMeta> findAllByProductId(Long productId, Pageable page);
}
