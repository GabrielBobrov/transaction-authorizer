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
class AccountAmountMealServiceAdapterTest {

    @InjectMocks
    private AccountAmountMealServiceAdapter accountAmountMealServiceAdapter;

    @Test
    void updateAmount_updatesAccountAndTransactionStatus_whenSufficientMealBalance() {
        AccountEntity account = AccountDummy.accountEntityBuilder().build();
        TransactionEntity transaction = TransactionDummy.transactionEntityBuilder().build();
        BigDecimal amount = BigDecimal.valueOf(50);

        accountAmountMealServiceAdapter.updateAmount(account, amount, transaction);

        assertEquals(TransactionStatusEnum.SUCCESS, transaction.getStatus());
        assertEquals(BigDecimal.valueOf(200), account.getCashAmount());
    }

    @Test
    void updateAmount_updatesAccountAndTransactionStatus_whenSufficientMealAndCashBalance() {
        AccountEntity account = AccountDummy.accountEntityBuilder()
                .mealAmount(BigDecimal.valueOf(50))
                .build();
        TransactionEntity transaction = TransactionDummy.transactionEntityBuilder().build();
        BigDecimal amount = BigDecimal.valueOf(150);

        accountAmountMealServiceAdapter.updateAmount(account, amount, transaction);

        assertEquals(TransactionStatusEnum.SUCCESS, transaction.getStatus());
        assertEquals(BigDecimal.valueOf(100), account.getCashAmount());
        assertEquals(BigDecimal.ZERO, account.getMealAmount());


    }

    @Test
    void updateAmount_throwsInsufficientTotalAmountException_whenInsufficientBalance() {
        AccountEntity account = AccountDummy.accountEntityBuilder()
                .mealAmount(BigDecimal.ZERO)
                .build();
        TransactionEntity transaction = TransactionDummy.transactionEntityBuilder().build();
        BigDecimal amount = BigDecimal.valueOf(250);

        assertThrows(InsufficientTotalAmountException.class, () -> accountAmountMealServiceAdapter.updateAmount(account, amount, transaction));
        assertEquals(TransactionStatusEnum.REJECTED, transaction.getStatus());
    }

    @Test
    void getAmountType_returnsMeal() {
        assertEquals(AmountTypeEnum.MEAL, accountAmountMealServiceAdapter.getAmountType());
    }
}