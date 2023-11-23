package com.c2c.service.exception;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException(String id, Throwable cause) {
        super("Tag with id=" + id + " not found", cause);
    }
}
