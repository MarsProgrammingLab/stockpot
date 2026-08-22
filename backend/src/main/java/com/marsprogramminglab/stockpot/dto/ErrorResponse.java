package com.marsprogramminglab.stockpot.dto;

import java.time.Instant;

public record ErrorResponse(int status, String message, Instant timestamp) {
}
