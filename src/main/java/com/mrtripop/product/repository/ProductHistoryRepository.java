package com.mrtripop.product.repository;

import com.mrtripop.product.models.db.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductHistoryRepository
    extends JpaRepository<ProductHistory, Long>, JpaSpecificationExecutor<ProductHistory> {}
