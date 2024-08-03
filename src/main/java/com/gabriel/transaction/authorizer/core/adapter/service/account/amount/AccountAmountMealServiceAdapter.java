package com.gabriel.transaction.authorizer.core.adapter.service.account.amount;

import com.gabriel.transaction.authorizer.core.enums.AmountTypeEnum;
import com.gabriel.transaction.authorizer.core.exception.InsufficientTotalAmountException;
import com.gabriel.transaction.authorizer.core.ports.in.service.account.IAccountAmountServicePort;
import com.gabriel.transaction.authorizer.infrastructure.entity.account.AccountEntity;
import com.gabriel.transaction.authorizer.infrastructure.entity.transaction.TransactionEntity;
import com.gabriel.transaction.authorizer.infrastructure.entity.transaction.enums.TransactionStatusEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.gabriel.transaction.authorizer.core.util.AmountUtil.isSufficientBalance;

/**
 * Service adapter for handling meal account amounts.
 */
@Slf4j
@Service
@AllArgsConstructor
public class AccountAmountMealServiceAdapter implements IAccountAmountServicePort {

    /**
     * Updates the amount in the account based on the transaction.
     *
     * @param account     the account entity
     * @param amount      the amount to be deducted
     * @param transaction the transaction entity
     * @throws InsufficientTotalAmountException if there is insufficient balance
     */
    @Override
    public void updateAmount(AccountEntity account, BigDecimal amount, TransactionEntity transaction) {
        log.info("Class {} method updateAmount", this.getClass().getName());
        log.info("account: {}", account);
        log.info("amount: {}", amount);
        log.info("transaction: {}", transaction);

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