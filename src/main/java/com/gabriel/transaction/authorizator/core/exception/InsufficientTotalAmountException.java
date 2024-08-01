package com.gabriel.transaction.authorizator.core.exception;

public class InsufficientTotalAmountException extends RuntimeException {

    public InsufficientTotalAmountException(String value) {
        super(value);
    }
}
