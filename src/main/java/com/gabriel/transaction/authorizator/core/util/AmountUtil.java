package com.gabriel.transaction.authorizator.core.util;

import com.gabriel.transaction.authorizator.core.enums.AmountTypeEnum;

import java.math.BigDecimal;
import java.util.Map;

import static com.gabriel.transaction.authorizator.core.ApplicationConstants.MCC_CASH;
import static com.gabriel.transaction.authorizator.core.ApplicationConstants.MCC_FOOD;
import static com.gabriel.transaction.authorizator.core.ApplicationConstants.MCC_FOOD_ALTERNATIVE;
import static com.gabriel.transaction.authorizator.core.ApplicationConstants.MCC_MEAL;
import static com.gabriel.transaction.authorizator.core.ApplicationConstants.MCC_MEAL_ALTERNATIVE;
import static com.gabriel.transaction.authorizator.core.ApplicationConstants.merchantMccMap;

public class AmountUtil {

    public static boolean isSufficientBalance(BigDecimal balance, BigDecimal amount) {
        return balance.compareTo(amount) >= 0;
    }

    public static String getDefaultMCCByAmountType(AmountTypeEnum amountType) {
        return switch (amountType) {
            case FOOD -> MCC_FOOD;
            case MEAL -> MCC_MEAL;
            case CASH -> MCC_CASH;
        };
    }

    public static boolean isValidMCCForAmountType(String mcc, AmountTypeEnum amountType) {
        return switch (amountType) {
            case FOOD -> MCC_FOOD.equals(mcc) || MCC_FOOD_ALTERNATIVE.equals(mcc);
            case MEAL -> MCC_MEAL.equals(mcc) || MCC_MEAL_ALTERNATIVE.equals(mcc);
            case CASH -> !MCC_FOOD.equals(mcc) && !MCC_FOOD_ALTERNATIVE.equals(mcc) && !MCC_MEAL.equals(mcc) && !MCC_MEAL_ALTERNATIVE.equals(mcc);
        };
    }

    public static AmountTypeEnum findAmountTypeByMerchant(String merchant) {
        return merchantMccMap.entrySet().stream()
                .filter(entry -> merchant.toUpperCase().contains(entry.getKey().toUpperCase()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(AmountTypeEnum.CASH);
    }
}
