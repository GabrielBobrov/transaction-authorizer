package com.gabriel.transaction.authorizator.core.exception;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException(String value) {
        super(value);
    }
}
