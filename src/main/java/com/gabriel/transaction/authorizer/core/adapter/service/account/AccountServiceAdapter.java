package com.gabriel.transaction.authorizer.core.adapter.service.account;

import com.gabriel.transaction.authorizer.core.ports.in.service.account.IAccountServicePort;
import com.gabriel.transaction.authorizer.core.ports.out.repository.account.IAccountRepositoryPort;
import com.gabriel.transaction.authorizer.infrastructure.entity.account.AccountEntity;
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

    /**
     * Finds an account by its ID.
     *
     * @param accountId the UUID of the account to find
     * @return the AccountEntity corresponding to the given ID
     */
    @Override
    public AccountEntity findById(UUID accountId) {
        log.info("Class {} method findById", this.getClass().getName());
        log.info("accountId: {}", accountId);

        return accountRepositoryPort.findById(accountId);
    }
}