package com.gabriel.transaction.authorizator.infrastructure.repository.adapter.transaction;

import com.gabriel.transaction.authorizator.infrastructure.entity.account.AccountEntity;
import com.gabriel.transaction.authorizator.infrastructure.entity.transaction.TransactionEntity;
import com.gabriel.transaction.authorizator.infrastructure.entity.transaction.enums.TransactionStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ISpringTransactionRepositoryAdapter extends JpaRepository<TransactionEntity, UUID> {

    Optional<TransactionEntity> findByIdAndStatusAndAccount(UUID id, TransactionStatusEnum status, AccountEntity account);
}
