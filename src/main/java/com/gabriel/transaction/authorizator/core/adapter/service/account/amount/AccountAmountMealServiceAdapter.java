package com.gabriel.transaction.authorizator.core.adapter.service.account.amount;

import com.gabriel.transaction.authorizator.core.enums.AmountTypeEnum;
import com.gabriel.transaction.authorizator.core.exception.InsufficientTotalAmountException;
import com.gabriel.transaction.authorizator.core.ports.in.service.account.IAccountAmountServicePort;
import com.gabriel.transaction.authorizator.infrastructure.entity.account.AccountEntity;
import com.gabriel.transaction.authorizator.infrastructure.entity.transaction.TransactionEntity;
import com.gabriel.transaction.authorizator.infrastructure.entity.transaction.enums.TransactionStatusEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.gabriel.transaction.authorizator.core.util.AmountUtil.isSufficientBalance;

@Slf4j
@Service
@AllArgsConstructor
public class AccountAmountMealServiceAdapter implements IAccountAmountServicePort {

    @Override
    public void updateAmount(AccountEntity account, BigDecimal amount, TransactionEntity transaction) {
        log.info("Class {} method updateAmount", this.getClass().getName());
        log.info("account: {}", account);
        log.info("amount: {}", amount);

        BigDecimal mealBalance = account.getMealAmount();
        BigDecimal cashBalance = account.getCashAmount();

        if (isSufficientBalance(mealBalance, amount)) {
            deductAmountFromMeal(account, amount);
            transaction.setStatus(TransactionStatusEnum.SUCCESS);
        } else {
            BigDecimal remainingAmount = amount.subtract(mealBalance);
            if (isSufficientBalance(cashBalance, remainingAmount)) {
                deductAmountFromMeal(account, mealBalance);
                deductAmountFromCash(account, remainingAmount);
                transaction.setStatus(TransactionStatusEnum.SUCCESS);
            } else {
                transaction.setStatus(TransactionStatusEnum.REJECTED);
                throw new InsufficientTotalAmountException("51");
            }
        }
    }

    @Override
    public AmountTypeEnum getAmountType() {
        return AmountTypeEnum.MEAL;
    }

    private void deductAmountFromMeal(AccountEntity account, BigDecimal amount) {
        account.setMealAmount(account.getMealAmount().subtract(amount));
    }

    private void deductAmountFromCash(AccountEntity account, BigDecimal amount) {
        account.setCashAmount(account.getCashAmount().subtract(amount));
    }
}