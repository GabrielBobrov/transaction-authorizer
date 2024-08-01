package com.gabriel.transaction.authorizator.core.exception;

public class InvalidTotalAmountException extends RuntimeException {

    public InvalidTotalAmountException(String value) {
        super(value);
    }
}
