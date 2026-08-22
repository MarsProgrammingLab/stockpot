package com.marsprogramminglab.stockpot.exception;

import com.marsprogramminglab.stockpot.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InventoryException.class)
    public ResponseEntity<ErrorResponse> handleInventoryException(InventoryException ex) {
        ErrorResponse error = new ErrorResponse(ex.getStatus().value(), ex.getMessage(), Instant.now());
        return ResponseEntity.status(ex.getStatus()).body(error);
    }
}
