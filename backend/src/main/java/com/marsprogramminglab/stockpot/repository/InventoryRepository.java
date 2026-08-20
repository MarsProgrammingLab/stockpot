package com.marsprogramminglab.stockpot.repository;

import com.marsprogramminglab.stockpot.entity.Category;
import com.marsprogramminglab.stockpot.entity.InventoryItem;
import com.marsprogramminglab.stockpot.entity.StorageLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// This repository manages InventoryItems of type Long
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
    // Spring Data JPA will parse the below method name by findBy + column name
    // Spring Data JPA will then generate a query filtering  on the column field and implement it at runtime
    // findByCategory Generates: WHERE category = ?
    List<InventoryItem> findByCategory(Category category);
    List<InventoryItem> findByStorageLocation(StorageLocation storageLocation);
    List<InventoryItem> findByName(String name);

}
