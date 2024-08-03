package com.gabriel.transaction.authorizer.core.exception;

public class InvalidMerchantException extends RuntimeException {

    public InvalidMerchantException(String value) {
        super(value);
    }
}
