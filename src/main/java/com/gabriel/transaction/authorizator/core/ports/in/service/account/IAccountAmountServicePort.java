package com.gabriel.transaction.authorizator.core.ports.in.service.account;


import com.gabriel.transaction.authorizator.core.enums.AmountTypeEnum;
import com.gabriel.transaction.authorizator.infrastructure.entity.account.AccountEntity;
import com.gabriel.transaction.authorizator.infrastructure.entity.transaction.TransactionEntity;

import java.math.BigDecimal;

public interface IAccountAmountServicePort {

    void updateAmount(AccountEntity accountId, BigDecimal amount, TransactionEntity transaction);

    AmountTypeEnum getAmountType();
}
