package com.gabriel.transaction.authorizator.core.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String value) {
        super(value);
    }
}
