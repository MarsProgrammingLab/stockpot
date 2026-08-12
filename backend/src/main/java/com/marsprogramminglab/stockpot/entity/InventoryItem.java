package com.marsprogramminglab.stockpot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class InventoryItem {

    @Id
    // IDENTITY strategy generates numeric IDs using an
    // identity column in the database. It doesn't require additional
    // queries to obtain the generated ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StorageLocation storageLocation;

    public InventoryItem() {}

    public InventoryItem(String name,  int quantity, StorageLocation storageLocation, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.storageLocation = storageLocation;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public StorageLocation getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(StorageLocation storageLocation) {
        this.storageLocation = storageLocation;
    }
}
