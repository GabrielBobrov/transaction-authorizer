package com.gabriel.transaction.authorizer.core.adapter.service.transaction;

import com.gabriel.transaction.authorizer.core.exception.InsufficientTotalAmountException;
import com.gabriel.transaction.authorizer.core.exception.InvalidMerchantException;
import com.gabriel.transaction.authorizer.core.exception.InvalidTotalAmountException;
import com.gabriel.transaction.authorizer.core.model.CreateTransactionAuthorizationModel;
import com.gabriel.transaction.authorizer.core.model.CreateTransactionAuthorizationResultModel;
import com.gabriel.transaction.authorizer.core.ports.in.service.account.IAccountAmountServicePort;
import com.gabriel.transaction.authorizer.core.ports.in.service.account.IAccountServicePort;
import com.gabriel.transaction.authorizer.core.ports.out.repository.transaction.ITransactionRepositoryPort;
import com.gabriel.transaction.authorizer.dummies.AccountDummy;
import com.gabriel.transaction.authorizer.dummies.TransactionDummy;
import com.gabriel.transaction.authorizer.infrastructure.entity.account.AccountEntity;
import com.gabriel.transaction.authorizer.infrastructure.entity.transaction.TransactionEntity;
import com.gabriel.transaction.authorizer.infrastructure.entity.transaction.enums.TransactionStatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransactionServiceAdapterTest {

    @MockBean
    private ITransactionRepositoryPort transactionRepositoryPort;

    @MockBean
    private IAccountServicePort accountServicePort;

    @MockBean
    private List<IAccountAmountServicePort> accountAmountStrategiesServicePorts;

    @Autowired
    private TransactionServiceAdapter transactionServiceAdapter;

    @Test
    void authorizeTransaction_updatesTransactionAndAccount_whenValidTransaction() {
        AccountEntity account = AccountDummy.accountEntityBuilder().build();
        TransactionEntity transaction = TransactionDummy.transactionEntityBuilder().build();
        UUID accountId = account.getId();
        UUID transactionId = transaction.getId();
        CreateTransactionAuthorizationModel model = TransactionDummy.createTransactionAuthorizationModelBuilder(accountId, transactionId).build();

        when(accountServicePort.findById(accountId)).thenReturn(account);
        when(transactionRepositoryPort.findByIdAndStatusAndAccount(transactionId, TransactionStatusEnum.PENDING_AUTHORIZATION, account)).thenReturn(transaction);

        CreateTransactionAuthorizationResultModel result = transactionServiceAdapter.authorizeTransaction(model);

        assertEquals("00", result.getCode());
    }

    @Test
    void authorizeTransaction_throwsInvalidTotalAmountException_whenTotalAmountMismatch() {
        AccountEntity account = AccountDummy.accountEntityBuilder().build();
        TransactionEntity transaction = TransactionDummy.transactionEntityBuilder()
                .totalAmount(BigDecimal.TEN)
                .build();
        UUID accountId = account.getId();
        UUID transactionId = transaction.getId();
        CreateTransactionAuthorizationModel model = TransactionDummy.createTransactionAuthorizationModelBuilder(accountId, transactionId).build();


        when(accountServicePort.findById(accountId)).thenReturn(account);
        when(transactionRepositoryPort.findByIdAndStatusAndAccount(transactionId, TransactionStatusEnum.PENDING_AUTHORIZATION, account)).thenReturn(transaction);

        assertThrows(InvalidTotalAmountException.class, () -> transactionServiceAdapter.authorizeTransaction(model));
    }

    @Test
    void authorizeTransaction_throwsInvalidMerchantException_whenMerchantMismatch() {
        AccountEntity account = AccountDummy.accountEntityBuilder().build();
        TransactionEntity transaction = TransactionDummy.transactionEntityBuilder().build();
        UUID accountId = account.getId();
        UUID transactionId = transaction.getId();
        CreateTransactionAuthorizationModel model = TransactionDummy.createTransactionAuthorizationModelBuilder(accountId, transactionId)
                .merchant("merchant")
                .build();

        when(accountServicePort.findById(accountId)).thenReturn(account);
        when(transactionRepositoryPort.findByIdAndStatusAndAccount(transactionId, TransactionStatusEnum.PENDING_AUTHORIZATION, account)).thenReturn(transaction);

        assertThrows(InvalidMerchantException.class, () -> transactionServiceAdapter.authorizeTransaction(model));
    }

    @Test
    void authorizeTransaction_updatesMcc_whenMccMismatch() {
        AccountEntity account = AccountDummy.accountEntityBuilder().build();
        TransactionEntity transaction = TransactionDummy.transactionEntityBuilder().build();
        UUID accountId = account.getId();
        UUID transactionId = transaction.getId();
        CreateTransactionAuthorizationModel model = TransactionDummy.createTransactionAuthorizationModelBuilder(accountId, transactionId)
                .mcc("5811")
                .build();

        when(accountServicePort.findById(accountId)).thenReturn(account);
        when(transactionRepositoryPort.findByIdAndStatusAndAccount(transactionId, TransactionStatusEnum.PENDING_AUTHORIZATION, account)).thenReturn(transaction);

        CreateTransactionAuthorizationResultModel result = transactionServiceAdapter.authorizeTransaction(model);

        assertEquals("00", result.getCode());
        assertEquals("7011", transaction.getMcc());
    }

    @Test
    void authorizeTransaction_throwsInsufficientTotalAmountException_whenInsufficientBalance() {
        AccountEntity account = AccountDummy.accountEntityBuilder()
                .mealAmount(BigDecimal.ZERO)
                .cashAmount(BigDecimal.ZERO)
                .build();
        TransactionEntity transaction = TransactionDummy.transactionEntityBuilder().build();
        UUID accountId = account.getId();
        UUID transactionId = transaction.getId();
        CreateTransactionAuthorizationModel model = TransactionDummy.createTransactionAuthorizationModelBuilder(accountId, transactionId).build();


        when(accountServicePort.findById(accountId)).thenReturn(account);
        when(transactionRepositoryPort.findByIdAndStatusAndAccount(transactionId, TransactionStatusEnum.PENDING_AUTHORIZATION, account)).thenReturn(transaction);

        assertThrows(InsufficientTotalAmountException.class, () -> transactionServiceAdapter.authorizeTransaction(model));
    }
}