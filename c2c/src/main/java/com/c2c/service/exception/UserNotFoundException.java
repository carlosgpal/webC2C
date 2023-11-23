package com.c2c.service.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id, Throwable cause) {
        super("User with id=" + id + " not found", cause);
    }
}
