package com.c2c.service.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String id, Throwable cause) {
        super("Product with id=" + id + " not found", cause);
    }
}
