package com.backend.service.exception;

// This is an exception that is thrown when an image is not found
public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(String id, Throwable cause) {
        super("Image with id=" + id + " not found", cause);
    }
}
