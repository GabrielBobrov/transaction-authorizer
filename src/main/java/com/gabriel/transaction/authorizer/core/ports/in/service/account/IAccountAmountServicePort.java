package com.gabriel.transaction.authorizer.core.ports.in.service.account;


import com.gabriel.transaction.authorizer.core.enums.AmountTypeEnum;
import com.gabriel.transaction.authorizer.infrastructure.entity.account.AccountEntity;
import com.gabriel.transaction.authorizer.infrastructure.entity.transaction.TransactionEntity;

import java.math.BigDecimal;

public interface IAccountAmountServicePort {

    void updateAmount(AccountEntity accountId, BigDecimal amount, TransactionEntity transaction);

    AmountTypeEnum getAmountType();
}
