package com.marsprogramminglab.stockpot.service;

import com.marsprogramminglab.stockpot.entity.*;
import com.marsprogramminglab.stockpot.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public InventoryItem save(InventoryItem item) {
        return inventoryRepository.save(item);
    }

    public List<InventoryItem> findAll() {
        return inventoryRepository.findAll();
    }

    public InventoryItem findById(Long id) {
        // .orElseThrow with lambda to construct and return exception only when item doesn't exist
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
    }


    public List<InventoryItem> findByCategory(Category category) {
        return inventoryRepository.findByCategory(category);
    }


    public List<InventoryItem> findByStorageLocation(StorageLocation storageLocation) {
        return inventoryRepository.findByStorageLocation(storageLocation);
    }


    public List<InventoryItem> findByName(String name) {
        return inventoryRepository.findByName(name);
    }


    public void deleteById(Long id) {
        if (!inventoryRepository.existsById(id)) {
            throw new RuntimeException("Item not found with id: " + id);
        }
        inventoryRepository.deleteById(id);
    }

    public boolean isStockLow(Long id, int minimumQuantity) {
        InventoryItem item = findById(id);
        return item.getQuantity() < minimumQuantity;
    }

    public InventoryItem updateQuantity(Long id, int difference) {
        InventoryItem item = findById(id);
        int newQuantity = item.getQuantity() + difference;

        if (newQuantity < 0) {
            throw new IllegalStateException("Quantity cannot be negative: " + newQuantity);
        } else {
            item.setQuantity(newQuantity);
        }
        return save(item);
    }
}
