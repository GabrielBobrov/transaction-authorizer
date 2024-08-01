package com.gabriel.transaction.authorizator.core.ports.out.repository.account;


import com.gabriel.transaction.authorizator.infrastructure.entity.account.AccountEntity;

import java.util.UUID;

public interface IAccountRepositoryPort {

    AccountEntity findById(UUID accountId);

    void save(AccountEntity account);
}
