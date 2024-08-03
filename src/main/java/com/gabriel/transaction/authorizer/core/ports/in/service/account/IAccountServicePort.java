package com.gabriel.transaction.authorizer.core.ports.in.service.account;


import com.gabriel.transaction.authorizer.infrastructure.entity.account.AccountEntity;

import java.util.UUID;

public interface IAccountServicePort {

AccountEntity findById(UUID accountId);
}
