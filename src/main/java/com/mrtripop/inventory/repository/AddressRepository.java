package com.mrtripop.inventory.repository;

import com.mrtripop.inventory.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

  Page<Address> findByUserId(Long userId, Pageable page);

  Optional<Address> findByUserIdAndId(Long userId, Long addressId);

  void deleteByUserIdAndId(Long userId, Long addressId);
}
