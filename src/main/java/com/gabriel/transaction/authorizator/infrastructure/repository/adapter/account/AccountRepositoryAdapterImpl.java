package com.gabriel.transaction.authorizator.infrastructure.repository.adapter.account;


import com.gabriel.transaction.authorizator.core.exception.AccountNotFoundException;
import com.gabriel.transaction.authorizator.core.ports.out.repository.account.IAccountRepositoryPort;
import com.gabriel.transaction.authorizator.infrastructure.entity.account.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Slf4j
@Component
@AllArgsConstructor
public class AccountRepositoryAdapterImpl implements IAccountRepositoryPort {

    private final ISpringAccountRepositoryAdapter springAccountRepositoryAdapter;


    @Override
    public AccountEntity findById(UUID accountId) {
        return springAccountRepositoryAdapter.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("07"));
    }

    @Override
    public void save(AccountEntity account) {
        springAccountRepositoryAdapter.save(account);
    }
}
