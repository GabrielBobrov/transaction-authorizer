package com.gabriel.transaction.authorizer.infrastructure.repository.adapter.account;

import com.gabriel.transaction.authorizer.core.exception.AccountNotFoundException;
import com.gabriel.transaction.authorizer.core.ports.out.repository.account.IAccountRepositoryPort;
import com.gabriel.transaction.authorizer.infrastructure.entity.account.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Adapter implementation for account repository.
 */
@Slf4j
@Component
@AllArgsConstructor
public class AccountRepositoryAdapterImpl implements IAccountRepositoryPort {

    private final ISpringAccountRepositoryAdapter springAccountRepositoryAdapter;

    /**
     * Finds an account by its ID.
     *
     * @param accountId the UUID of the account to find
     * @return the AccountEntity corresponding to the given ID
     * @throws AccountNotFoundException if the account is not found
     */
    @Override
    public AccountEntity findById(UUID accountId) {
        log.info("Class {} method findById", this.getClass().getName());
        log.info("accountId: {}", accountId);

        return springAccountRepositoryAdapter.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("07"));
    }
}