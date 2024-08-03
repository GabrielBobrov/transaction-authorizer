package com.gabriel.transaction.authorizer.dummies;

import com.gabriel.transaction.authorizer.infrastructure.entity.transaction.TransactionEntity;
import com.gabriel.transaction.authorizer.infrastructure.entity.transaction.enums.TransactionStatusEnum;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class TransactionDummy {

    public static TransactionEntity.TransactionEntityBuilder transactionEntityBuilder() {
        return TransactionEntity.builder()
                .id(UUID.randomUUID())
                .account(AccountDummy.accountEntityBuilder().build())
                .status(TransactionStatusEnum.PENDING_AUTHORIZATION)
                .totalAmount(BigDecimal.valueOf(100))
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .mcc("Sample MCC")
                .merchant("Sample Merchant");
    }

}
