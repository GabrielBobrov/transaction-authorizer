package com.gabriel.transaction.authorizer.core.exception;

public class InsufficientTotalAmountException extends RuntimeException {

    public InsufficientTotalAmountException(String value) {
        super(value);
    }
}
