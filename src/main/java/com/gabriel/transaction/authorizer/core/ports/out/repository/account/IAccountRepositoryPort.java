package com.gabriel.transaction.authorizer.core.ports.out.repository.account;


import com.gabriel.transaction.authorizer.infrastructure.entity.account.AccountEntity;

import java.util.UUID;

public interface IAccountRepositoryPort {

    AccountEntity findById(UUID accountId);
}
