package com.mrtripop.inventory.location.repositories;

import com.mrtripop.inventory.location.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {}
