package com.gabriel.transaction.authorizer.core.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String value) {
        super(value);
    }
}
