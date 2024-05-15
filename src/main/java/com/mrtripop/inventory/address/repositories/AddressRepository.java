package com.mrtripop.inventory.address.repositories;

import com.mrtripop.inventory.address.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {}
