package com.gabriel.transaction.authorizator.entrypoint;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class UrlConstant {
    public static final String AUTHORIZATION_URI = "/authorizations";
    public static final String TRANSACTION_URI = "/transactions";
    public static final String TRANSACTION_AUTHORIZATION_URI = TRANSACTION_URI + "/{id}" + AUTHORIZATION_URI;

}
