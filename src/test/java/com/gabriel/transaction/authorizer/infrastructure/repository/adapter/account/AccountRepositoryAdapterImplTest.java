package com.gabriel.transaction.authorizer.infrastructure.repository.adapter.account;

import com.gabriel.transaction.authorizer.core.exception.AccountNotFoundException;
import com.gabriel.transaction.authorizer.dummies.AccountDummy;
import com.gabriel.transaction.authorizer.infrastructure.entity.account.AccountEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountRepositoryAdapterImplTest {

    @Mock
    private ISpringAccountRepositoryAdapter springAccountRepositoryAdapter;

    @InjectMocks
    private AccountRepositoryAdapterImpl accountRepositoryAdapter;

    @Test
    void findById_returnsAccountEntity_whenAccountExists() {
        UUID accountId = UUID.randomUUID();
        AccountEntity accountEntity = AccountDummy.accountEntityBuilder().build();
        when(springAccountRepositoryAdapter.findById(accountId)).thenReturn(Optional.of(accountEntity));

        AccountEntity result = accountRepositoryAdapter.findById(accountId);

        assertEquals(accountEntity, result);
    }

    @Test
    void findById_throwsAccountNotFoundException_whenAccountDoesNotExist() {
        UUID accountId = UUID.randomUUID();
        when(springAccountRepositoryAdapter.findById(accountId)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountRepositoryAdapter.findById(accountId));
    }
}