package com.c2c.service.exception;

public class TechnicalException extends RuntimeException {
    public TechnicalException(Throwable cause) {
        super("A technical problem occured: ", cause);
    }
}
