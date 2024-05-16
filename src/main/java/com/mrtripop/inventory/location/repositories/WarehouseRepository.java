package com.mrtripop.inventory.location.repositories;

import com.mrtripop.inventory.location.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {}
