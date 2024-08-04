package com.gabriel.transaction.authorizer.core.adapter.service.account;

import com.gabriel.transaction.authorizer.core.ports.out.repository.account.IAccountRepositoryPort;
import com.gabriel.transaction.authorizer.dummies.AccountDummy;
import com.gabriel.transaction.authorizer.infrastructure.entity.account.AccountEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceAdapterTest {

    @Mock
    private IAccountRepositoryPort accountRepositoryPort;

    @InjectMocks
    private AccountServiceAdapter accountServiceAdapter;

    @Test
    void findById_returnsAccountEntity_whenAccountExists() {
        UUID accountId = UUID.randomUUID();
        AccountEntity accountEntity = AccountDummy.accountEntityBuilder().build();
        when(accountRepositoryPort.findById(accountId)).thenReturn(accountEntity);

        AccountEntity result = accountServiceAdapter.findById(accountId);

        assertNotNull(result);
        assertEquals(accountEntity, result);
    }
}