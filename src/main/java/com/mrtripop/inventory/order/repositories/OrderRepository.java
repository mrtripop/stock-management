package com.mrtripop.inventory.order.repositories;

import com.mrtripop.inventory.order.models.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
  Page<Order> findByUserId(Long userId, Pageable page);

  Optional<Order> findByUserIdAndId(Long userId, Long orderId);

  @Transactional
  void deleteByUserId(Long userId);

  @Transactional
  void deleteByUserIdAndId(Long userId, Long orderId);
}
