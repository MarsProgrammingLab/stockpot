package com.marsprogramminglab.stockpot.exception;

import org.springframework.http.HttpStatus;

public class InvalidQuantityUpdateException extends InventoryException {
    public InvalidQuantityUpdateException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
