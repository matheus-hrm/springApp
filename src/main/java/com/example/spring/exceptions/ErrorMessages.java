package com.example.spring.exceptions;

public enum ErrorMessages {

    PRODUCT_NOT_FOUND("Product not found"),
    NAME_REQUIRED("Name is required"),
    DESCRIPTION_LENGTH("Description should be more than 20 characters"),
    PRICE_NOT_NEGATIVE("Price cannot be negative");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
