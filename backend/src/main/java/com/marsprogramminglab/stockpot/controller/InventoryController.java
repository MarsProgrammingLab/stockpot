package com.marsprogramminglab.stockpot.controller;

import com.marsprogramminglab.stockpot.dto.StockStatus;
import com.marsprogramminglab.stockpot.entity.Category;
import com.marsprogramminglab.stockpot.entity.InventoryItem;
import com.marsprogramminglab.stockpot.entity.StorageLocation;
import com.marsprogramminglab.stockpot.exception.MultipleFiltersAppliedException;
import com.marsprogramminglab.stockpot.service.InventoryService;
import com.marsprogramminglab.stockpot.dto.QuantityUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


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
    List<InventoryItem> search(@RequestParam(required = false) Category category,
                               @RequestParam(required = false) StorageLocation location,
                               @RequestParam(required = false) String name) {

        long providedFilters = Stream.of(category, location, name)
                .filter(Objects::nonNull)
                .count();

        if (providedFilters > 1) {
            throw new MultipleFiltersAppliedException("Only one filter parameter may be supplied at a time.");
        }

        if (category != null) {
            return inventoryService.findByCategory(category);
        } else if (location != null) {
            return inventoryService.findByStorageLocation(location);
        } else if (name != null) {
            return inventoryService.findByName(name);
        } else {
            return inventoryService.findAll();
        }
    }

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
    @PatchMapping("/{id}/quantity")
    InventoryItem updateQuantity(@PathVariable Long id, @RequestBody QuantityUpdateRequest request) {
        return inventoryService.updateQuantity(id, request.difference());
    }

    // GET /api/items/{id}/low-stock?minimumQuantity=X
    @GetMapping("/{id}/low-stock")
    StockStatus checkStockStatus(@PathVariable Long id, @RequestParam int minimumQuantity) {
        boolean low = inventoryService.isStockLow(id, minimumQuantity);
        int currentQuantity = inventoryService.findById(id).getQuantity();
        return new StockStatus(low, currentQuantity, minimumQuantity);
    }
}
