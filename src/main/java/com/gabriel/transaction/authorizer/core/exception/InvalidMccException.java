package com.gabriel.transaction.authorizer.core.exception;

public class InvalidMccException extends RuntimeException {

    public InvalidMccException(String value) {
        super(value);
    }
}
