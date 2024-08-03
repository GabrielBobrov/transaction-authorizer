package com.gabriel.transaction.authorizer.core.ports.out.repository.transaction;


import com.gabriel.transaction.authorizer.infrastructure.entity.account.AccountEntity;
import com.gabriel.transaction.authorizer.infrastructure.entity.transaction.TransactionEntity;
import com.gabriel.transaction.authorizer.infrastructure.entity.transaction.enums.TransactionStatusEnum;

import java.util.UUID;

public interface ITransactionRepositoryPort {

    TransactionEntity findByIdAndStatusAndAccount(UUID transactionId, TransactionStatusEnum transactionStatusEnum, AccountEntity account);
}
