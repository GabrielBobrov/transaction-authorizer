package com.gabriel.transaction.authorizer.infrastructure.repository.adapter.transaction;

import com.gabriel.transaction.authorizer.core.exception.TransactionNotFoundException;
import com.gabriel.transaction.authorizer.dummies.AccountDummy;
import com.gabriel.transaction.authorizer.dummies.TransactionDummy;
import com.gabriel.transaction.authorizer.infrastructure.entity.account.AccountEntity;
import com.gabriel.transaction.authorizer.infrastructure.entity.transaction.TransactionEntity;
import com.gabriel.transaction.authorizer.infrastructure.entity.transaction.enums.TransactionStatusEnum;
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
class TransactionRepositoryAdapterImplTest {

    @Mock
    private ISpringTransactionRepositoryAdapter springTransactionRepositoryAdapter;

    @InjectMocks
    private TransactionRepositoryAdapterImpl transactionRepositoryAdapter;

    @Test
    void findByIdAndStatusAndAccount_returnsTransactionEntity_whenTransactionExists() {
        TransactionEntity transactionEntity = TransactionDummy.createTransactionEntityBuilder().build();
        TransactionStatusEnum status = TransactionStatusEnum.SUCCESS;
        AccountEntity account = AccountDummy.accountEntityBuilder().build();
        when(springTransactionRepositoryAdapter.findByIdAndStatusAndAccount(transactionEntity.getId(), status, account))
                .thenReturn(Optional.of(transactionEntity));

        TransactionEntity result = transactionRepositoryAdapter.findByIdAndStatusAndAccount(transactionEntity.getId(), status, account);

        assertEquals(transactionEntity, result);
    }

    @Test
    void findByIdAndStatusAndAccount_throwsTransactionNotFoundException_whenTransactionDoesNotExist() {
        UUID transactionId = UUID.randomUUID();
        TransactionStatusEnum status = TransactionStatusEnum.SUCCESS;
        AccountEntity account = AccountDummy.accountEntityBuilder().build();
        when(springTransactionRepositoryAdapter.findByIdAndStatusAndAccount(transactionId, status, account))
                .thenReturn(Optional.empty());

        assertThrows(TransactionNotFoundException.class, () -> transactionRepositoryAdapter.findByIdAndStatusAndAccount(transactionId, status, account));
    }
}