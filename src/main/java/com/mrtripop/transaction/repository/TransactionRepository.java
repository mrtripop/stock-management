package com.mrtripop.transaction.repository;

import com.mrtripop.transaction.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  Optional<List<Transaction>> findByUserId(Long userId);
}
