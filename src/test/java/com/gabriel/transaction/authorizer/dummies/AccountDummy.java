package com.gabriel.transaction.authorizer.dummies;

import com.gabriel.transaction.authorizer.infrastructure.entity.account.AccountEntity;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountDummy {

    public static AccountEntity.AccountEntityBuilder accountEntityBuilder() {
        return AccountEntity.builder()
                .id(UUID.randomUUID())
                .foodAmount(BigDecimal.valueOf(100))
                .cashAmount(BigDecimal.valueOf(200));
    }
}
