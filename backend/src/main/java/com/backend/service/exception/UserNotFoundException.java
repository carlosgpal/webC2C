package com.backend.service.exception;

// This is an exception that is thrown when a user is not found
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id, Throwable cause) {
        super("User with id=" + id + " not found", cause);
    }
}
