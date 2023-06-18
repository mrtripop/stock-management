package com.learn.controller;

import com.learn.model.Order;
import com.learn.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/inventory")
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping("/users/{userId}/orders")
  public ResponseEntity<List<Order>> retrieveOrders(
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "10") Integer size,
      @RequestParam(defaultValue = "asc") String orderBy,
      @PathVariable(name = "userId", required = true) Long userId) {
    try {
      List<Order> result = orderService.retrieveUserOrders(userId, page, size, orderBy);
      if (result == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      log.error(e.toString());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/users/{userId}/orders/{orderId}")
  public ResponseEntity<Order> retrieveUserOrderById(
      @PathVariable(name = "userId", required = true) Long userId,
      @PathVariable(name = "orderId", required = true) Long orderId) {
    try {
      Order result = orderService.retrieveUserOrderById(userId, orderId);
      if (result == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      log.error(e.toString());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/users/{userId}/orders")
  public ResponseEntity<Order> createNewOrder(
      @PathVariable(name = "userId") Long userId, @RequestBody Order newOrder) {
    try {
      Order order = orderService.createUserOrder(userId, newOrder);
      if (order == null) return new ResponseEntity<>(HttpStatus.ACCEPTED);
      return new ResponseEntity<>(order, HttpStatus.OK);
    } catch (Exception e) {
      log.error(e.toString());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/users/{userId}/orders/{orderId}")
  public ResponseEntity<Order> updateOrder(
      @PathVariable(name = "userId") Long userId,
      @PathVariable(name = "orderId") Long orderId,
      @RequestBody Order newOrder) {
    try {
      Order order = orderService.updateUserOrder(userId, orderId, newOrder);
      if (order == null) return new ResponseEntity<>(HttpStatus.ACCEPTED);
      return new ResponseEntity<>(order, HttpStatus.OK);
    } catch (Exception e) {
      log.error(e.toString());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/users/{userId}/orders")
  public ResponseEntity<Boolean> deleteUserOrder(@PathVariable(name = "userId") Long userId) {
    try {
      boolean order = orderService.deleteUserOrder(userId);
      if (!order) return new ResponseEntity<>(HttpStatus.ACCEPTED);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      log.error(e.toString());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/users/{userId}/orders/{orderId}")
  public ResponseEntity<Boolean> deleteUserOrderById(
      @PathVariable(name = "userId") Long userId, @PathVariable(name = "orderId") Long orderId) {
    try {
      boolean order = orderService.deleteUserOrderById(userId, orderId);
      if (!order) return new ResponseEntity<>(HttpStatus.ACCEPTED);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      log.error(e.toString());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
