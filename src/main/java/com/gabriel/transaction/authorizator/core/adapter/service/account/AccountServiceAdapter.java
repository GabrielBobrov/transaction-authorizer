package com.gabriel.transaction.authorizator.core.adapter.service.account;


import com.gabriel.transaction.authorizator.core.ports.in.service.account.IAccountServicePort;
import com.gabriel.transaction.authorizator.core.ports.out.repository.account.IAccountRepositoryPort;
import com.gabriel.transaction.authorizator.infrastructure.entity.account.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Adapter for the account service.
 */
@Slf4j
@Service
@AllArgsConstructor
public class AccountServiceAdapter implements IAccountServicePort {

    private final IAccountRepositoryPort accountRepositoryPort;


    @Override
    public AccountEntity findById(UUID accountId) {
        log.info("Class {} method findById", this.getClass().getName());
        log.info("accountId: {}", accountId);

        return accountRepositoryPort.findById(accountId);
    }
}
