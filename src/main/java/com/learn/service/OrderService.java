package com.learn.service;

import com.learn.helper.DatabaseHelper;
import com.learn.model.Order;
import com.learn.model.User;
import com.learn.repository.OrderRepository;
import com.learn.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderService {

  private final UserRepository userRepository;
  private final OrderRepository orderRepository;

  public OrderService(UserRepository userRepository, OrderRepository orderRepository) {
    this.userRepository = userRepository;
    this.orderRepository = orderRepository;
  }

  public List<Order> retrieveUserOrders(Long userId, Integer page, Integer size, String orderBy) {
    try {
      boolean existUser = userRepository.existsById(userId);
      if (existUser) {
        Pageable pageSize = DatabaseHelper.initPageableWithSort(page, size, orderBy);
        Page<Order> orders = orderRepository.findByUserId(userId, pageSize);
        log.debug(orders.toString());
        return orders.stream().toList();
      } else {
        return null;
      }
    } catch (Exception e) {
      log.error(e.toString());
      throw new RuntimeException("RetrieveOrdersException", e.getCause());
    }
  }

  public Order retrieveUserOrderById(Long userId, Long orderId) {
    try {
      boolean existUser = userRepository.existsById(userId);
      if (!existUser) return null;
      Optional<Order> order = orderRepository.findByUserIdAndId(userId, orderId);
      return order.orElse(null);
    } catch (Exception e) {
      log.error(e.toString());
      throw new RuntimeException("RetrieveOrderByIdException", e.getCause());
    }
  }

  public Order createUserOrder(Long userId, Order order) {
    try {
      Optional<User> existUser = userRepository.findById(userId);
      if (existUser.isEmpty()) return null;
      order.setCreatedAt(ZonedDateTime.now());
      order.setUpdatedAt(ZonedDateTime.now());
      order.setUser(existUser.get());
      return orderRepository.save(order);
    } catch (Exception e) {
      log.error(e.toString());
      throw new RuntimeException("CreateOrderException", e.getCause());
    }
  }

  public Order updateUserOrder(Long userId, Long orderId, Order newOrder) {
    try {
      Optional<User> existedUser = userRepository.findById(userId);
      if (existedUser.isEmpty()) return null;

      Optional<Order> order = orderRepository.findByUserIdAndId(userId, orderId);
      if (order.isPresent()) {
        Order originalOrder = order.get();
        originalOrder.setType(newOrder.getType());
        originalOrder.setStatus(newOrder.getStatus());
        originalOrder.setSubTotal(newOrder.getSubTotal());
        originalOrder.setItemDiscount(newOrder.getItemDiscount());
        originalOrder.setTax(newOrder.getTax());
        originalOrder.setShipping(newOrder.getShipping());
        originalOrder.setTotal(newOrder.getTotal());
        originalOrder.setDiscount(newOrder.getDiscount());
        originalOrder.setGrandTotal(newOrder.getGrandTotal());
        originalOrder.setPromo(newOrder.getPromo());
        originalOrder.setContent(newOrder.getContent());
        originalOrder.setUpdatedAt(newOrder.getUpdatedAt());
        return orderRepository.save(originalOrder);
      } else {
        newOrder.setCreatedAt(ZonedDateTime.now());
        newOrder.setUser(existedUser.get());
        return orderRepository.save(newOrder);
      }
    } catch (Exception e) {
      log.error(e.toString());
      throw new RuntimeException("UpdateUserOrderByIdException", e.getCause());
    }
  }

  public boolean deleteUserOrder(Long userId) {
    try {
      boolean existedUser = userRepository.existsById(userId);
      if (!existedUser) return false;
      return orderRepository.deleteByUserId(userId);
    } catch (Exception e) {
      log.error(e.toString());
      throw new RuntimeException("DeleteUserOrderException", e.getCause());
    }
  }

  public boolean deleteUserOrderById(Long userId, Long orderId) {
    try {
      boolean existedUser = userRepository.existsById(userId);
      if (!existedUser) return false;
      return orderRepository.deleteByUserIdAndId(userId, orderId);
    } catch (Exception e) {
      log.error(e.toString());
      throw new RuntimeException("DeleteUserOrderByIdException", e.getCause());
    }
  }
}
