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

@Slf4j
@Service
@AllArgsConstructor
public class AccountAmountFoodServiceAdapter implements IAccountAmountServicePort {

    @Override
    public void updateAmount(AccountEntity account, BigDecimal amount, TransactionEntity transaction) {
        log.info("Class {} method updateAmount", this.getClass().getName());
        log.info("account: {}", account);
        log.info("amount: {}", amount);
        log.info("transaction: {}", transaction);

        BigDecimal foodBalance = account.getFoodAmount();
        BigDecimal cashBalance = account.getCashAmount();

        if (isSufficientBalance(foodBalance, amount)) {
            deductAmountFromFood(account, amount);
            transaction.setStatus(TransactionStatusEnum.SUCCESS);
        } else {
            BigDecimal remainingAmount = amount.subtract(foodBalance);
            if (isSufficientBalance(cashBalance, remainingAmount)) {
                deductAmountFromFood(account, foodBalance);
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
        return AmountTypeEnum.FOOD;
    }

    private void deductAmountFromFood(AccountEntity account, BigDecimal amount) {
        account.setFoodAmount(account.getFoodAmount().subtract(amount));
    }

    private void deductAmountFromCash(AccountEntity account, BigDecimal amount) {
        account.setCashAmount(account.getCashAmount().subtract(amount));
    }
}