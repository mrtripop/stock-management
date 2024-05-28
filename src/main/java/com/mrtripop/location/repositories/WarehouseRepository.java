package com.mrtripop.location.repositories;

import com.mrtripop.location.models.entities.Address;
import com.mrtripop.location.models.entities.Warehouse;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
  Optional<Warehouse> findByAddress(Address address);
}
