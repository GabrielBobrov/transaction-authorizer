package com.gabriel.transaction.authorizator.infrastructure.repository.adapter.account;

import com.gabriel.transaction.authorizator.infrastructure.entity.account.AccountEntity;
import com.gabriel.transaction.authorizator.infrastructure.entity.transaction.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ISpringAccountRepositoryAdapter extends JpaRepository<AccountEntity, UUID> {

}
