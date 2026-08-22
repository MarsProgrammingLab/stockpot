package com.marsprogramminglab.stockpot.dto;

public record StockStatus(boolean low, int currentQuantity, int minimumQuantity) {}
