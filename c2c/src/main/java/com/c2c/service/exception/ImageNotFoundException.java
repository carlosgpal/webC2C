package com.c2c.service.exception;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(String id, Throwable cause) {
        super("Image with id=" + id + " not found", cause);
    }
}
