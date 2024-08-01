package com.gabriel.transaction.authorizator.core.ports.out.repository.transaction;


import com.gabriel.transaction.authorizator.infrastructure.entity.account.AccountEntity;
import com.gabriel.transaction.authorizator.infrastructure.entity.transaction.TransactionEntity;
import com.gabriel.transaction.authorizator.infrastructure.entity.transaction.enums.TransactionStatusEnum;

import java.util.UUID;

public interface ITransactionRepositoryPort {

    TransactionEntity findByIdAndStatusAndAccount(UUID transactionId, TransactionStatusEnum transactionStatusEnum, AccountEntity account);
}
