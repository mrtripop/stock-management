package com.learn.repository;

import com.learn.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRespository extends JpaRepository<Transaction, Long> {
}
