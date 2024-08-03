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
class AccountAmountCashServiceAdapterTest {

    @InjectMocks
    private AccountAmountCashServiceAdapter accountAmountCashServiceAdapter;

    @Test
    void updateAmount_updatesAccountAndTransactionStatus_whenSufficientBalance() {
        AccountEntity account = AccountDummy.accountEntityBuilder().build();
        TransactionEntity transaction = TransactionDummy.transactionEntityBuilder().build();
        BigDecimal amount = BigDecimal.valueOf(50);

        accountAmountCashServiceAdapter.updateAmount(account, amount, transaction);

        assertEquals(TransactionStatusEnum.SUCCESS, transaction.getStatus());
    }

    @Test
    void updateAmount_throwsInsufficientTotalAmountException_whenInsufficientBalance() {
        AccountEntity account = AccountDummy.accountEntityBuilder()
                .cashAmount(BigDecimal.ZERO)
                .build();

        TransactionEntity transaction = TransactionDummy.transactionEntityBuilder().build();

        BigDecimal amount = BigDecimal.valueOf(150);

        assertThrows(InsufficientTotalAmountException.class, () -> accountAmountCashServiceAdapter.updateAmount(account, amount, transaction));
        assertEquals(TransactionStatusEnum.REJECTED, transaction.getStatus());

    }

    @Test
    void getAmountType_returnsCash() {
        assertEquals(AmountTypeEnum.CASH, accountAmountCashServiceAdapter.getAmountType());
    }
}