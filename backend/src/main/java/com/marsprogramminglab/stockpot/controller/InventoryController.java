package com.marsprogramminglab.stockpot.controller;

import com.marsprogramminglab.stockpot.entity.InventoryItem;
import com.marsprogramminglab.stockpot.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/items")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // GET /api/items/{id} -> findById(id)
    @GetMapping("/{id}")
    InventoryItem findById(@PathVariable Long id) { // @PathVariable extracts 'id' from URL
        return inventoryService.findById(id);
    }

    // GET /api/items -> findAll()
    @GetMapping
    List<InventoryItem> findAll() {
        return inventoryService.findAll();
    }

    // GET /api/items?category=X   → findByCategory(X)

    // GET /api/items?location=X   → findByStorageLocation(X)

    // GET /api/items?name=X       → findByName(X)

    // POST /api/items save(id)
    @PostMapping
    InventoryItem save(@RequestBody InventoryItem item) {
        return inventoryService.save(item);
    }

    // DELETE /api/items/{id} deleteById(id)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) {
        inventoryService.deleteById(id);
    }

    // PATCH /api/items/{id}/quantity updateQuantity(id, difference)

    // GET /api/items/{id}/low-stock?minimumQuantity=X
}
