package com.marsprogramminglab.stockpot.exception;

import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends InventoryException {
    public ItemNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
