package com.gabriel.transaction.authorizer.core.adapter.service.account.amount;

import com.gabriel.transaction.authorizer.core.enums.AmountTypeEnum;
import com.gabriel.transaction.authorizer.core.exception.InsufficientTotalAmountException;
import com.gabriel.transaction.authorizer.dummies.AccountDummy;
import com.gabriel.transaction.authorizer.dummies.TransactionDummy;
import com.gabriel.transaction.authorizer.infrastructure.entity.account.AccountEntity;
import com.gabriel.transaction.authorizer.infrastructure.entity.transaction.TransactionEntity;
import com.gabriel.transaction.authorizer.infrastructure.entity.transaction.enums.TransactionStatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AccountAmountFoodServiceAdapterTest {

    @InjectMocks
    private AccountAmountFoodServiceAdapter accountAmountFoodServiceAdapter;

    @Test
    void updateAmount_updatesAccountAndTransactionStatus_whenSufficientFoodBalance() {
        AccountEntity account = AccountDummy.accountEntityBuilder().build();
        TransactionEntity transaction = TransactionDummy.transactionEntityBuilder().build();
        BigDecimal amount = BigDecimal.valueOf(50);

        accountAmountFoodServiceAdapter.updateAmount(account, amount, transaction);

        assertEquals(TransactionStatusEnum.SUCCESS, transaction.getStatus());
        assertEquals(BigDecimal.valueOf(200), account.getCashAmount());
    }

    @Test
    void updateAmount_updatesAccountAndTransactionStatus_whenSufficientFoodAndCashBalance() {
        AccountEntity account = AccountDummy.accountEntityBuilder().build();
        TransactionEntity transaction = TransactionDummy.transactionEntityBuilder().build();
        BigDecimal amount = BigDecimal.valueOf(150);

        accountAmountFoodServiceAdapter.updateAmount(account, amount, transaction);

        assertEquals(TransactionStatusEnum.SUCCESS, transaction.getStatus());
        assertEquals(BigDecimal.valueOf(150), account.getCashAmount());
        assertEquals(BigDecimal.ZERO, account.getFoodAmount());
    }

    @Test
    void updateAmount_throwsInsufficientTotalAmountException_whenInsufficientBalance() {
        AccountEntity account = AccountDummy.accountEntityBuilder()
                .foodAmount(BigDecimal.TEN)
                .build();
        TransactionEntity transaction = TransactionDummy.transactionEntityBuilder().build();
        BigDecimal amount = BigDecimal.valueOf(250);

        assertThrows(InsufficientTotalAmountException.class, () -> accountAmountFoodServiceAdapter.updateAmount(account, amount, transaction));
        assertEquals(TransactionStatusEnum.REJECTED, transaction.getStatus());
    }

    @Test
    void getAmountType_returnsFood() {
        assertEquals(AmountTypeEnum.FOOD, accountAmountFoodServiceAdapter.getAmountType());
    }
}