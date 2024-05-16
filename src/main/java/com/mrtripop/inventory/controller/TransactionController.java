package com.mrtripop.inventory.controller;

import com.mrtripop.inventory.service.TransactionService;
import com.mrtripop.inventory.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/inventory/transactions")
public class TransactionController {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @GetMapping
  public ResponseEntity<List<Transaction>> getTransaction(
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "10") Integer size,
      @RequestParam(defaultValue = "ASC") Sort.Direction orderBy) {
    try {
      List<Transaction> transactions = this.transactionService.getTransactions(page, size, orderBy);
      return new ResponseEntity<>(transactions, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{userId}/users")
  public ResponseEntity<List<Transaction>> getTransactionByUserId(
      @PathVariable(name = "userId") Long userId) {
    try {
      List<Transaction> transactions = this.transactionService.getTransactionByUserId(userId);
      return new ResponseEntity<>(transactions, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{transactionId}")
  public ResponseEntity<Transaction> getTransactionById(
      @PathVariable(name = "transactionId") Long transactionId) {
    try {
      Transaction transaction = this.transactionService.getTransactionById(transactionId);
      if (transaction != null) {
        return new ResponseEntity<>(transaction, HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{transactionId}")
  public ResponseEntity<Transaction> updateTransactionById(
      @PathVariable(name = "transactionId") Long transactionId,
      @RequestBody Transaction updateBody) {
    try {
      Transaction updateOrigin = transactionService.updateTransaction(transactionId, updateBody);
      return new ResponseEntity<>(updateOrigin, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
