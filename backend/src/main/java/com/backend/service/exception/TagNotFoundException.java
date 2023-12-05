package com.backend.service.exception;

// This is an exception that is thrown when a tag is not found
public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException(String id, Throwable cause) {
        super("Tag with id=" + id + " not found", cause);
    }
}
