package com.example.spring.exceptions;

public enum ErrorMessages {
    PRODUCT_NOT_FOUND("Product not found");
    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
