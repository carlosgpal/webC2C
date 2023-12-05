package com.backend.service.exception;

// This is an exception that is thrown when a technical problem occurs
public class TechnicalException extends RuntimeException {
    public TechnicalException(Throwable cause) {
        super("A technical problem occured: ", cause);
    }
}
