package com.marsprogramminglab.stockpot.exception;

import org.springframework.http.HttpStatus;

public class MultipleFiltersAppliedException extends InventoryException {
    public MultipleFiltersAppliedException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
