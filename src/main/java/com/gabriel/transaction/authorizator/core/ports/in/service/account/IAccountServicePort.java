package com.gabriel.transaction.authorizator.core.ports.in.service.account;


import com.gabriel.transaction.authorizator.infrastructure.entity.account.AccountEntity;

import java.util.UUID;

public interface IAccountServicePort {

AccountEntity findById(UUID accountId);
}
