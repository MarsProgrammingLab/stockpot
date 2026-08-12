package com.marsprogramminglab.stockpot.repository;

import com.marsprogramminglab.stockpot.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

// This repository manages InventoryItems of type Long
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
}
