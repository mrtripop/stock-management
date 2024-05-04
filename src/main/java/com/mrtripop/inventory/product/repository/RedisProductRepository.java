package com.mrtripop.inventory.product.repository;

import com.mrtripop.inventory.product.models.ProductCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisProductRepository extends CrudRepository<ProductCache, Long> {}
