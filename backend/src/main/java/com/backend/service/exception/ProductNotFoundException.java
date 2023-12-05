package com.backend.service.exception;

// This is an exception that is thrown when a product is not found
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String id, Throwable cause) {
        super("Product with id=" + id + " not found", cause);
    }
}
