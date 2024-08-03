package com.gabriel.transaction.authorizer.core.exception;

public class InvalidTotalAmountException extends RuntimeException {

    public InvalidTotalAmountException(String value) {
        super(value);
    }
}
