package com.gabriel.transaction.authorizer.core.exception;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException(String value) {
        super(value);
    }
}
