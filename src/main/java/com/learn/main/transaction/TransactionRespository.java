package com.learn.main.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRespository extends JpaRepository<Transaction, Long> {
}
