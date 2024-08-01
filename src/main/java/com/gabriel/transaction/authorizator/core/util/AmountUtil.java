package com.gabriel.transaction.authorizator.core.util;

import com.gabriel.transaction.authorizator.core.ApplicationConstants;
import com.gabriel.transaction.authorizator.core.enums.AmountTypeEnum;

import java.math.BigDecimal;
import java.util.Map;

public class AmountUtil {

    public static boolean isSufficientBalance(BigDecimal balance, BigDecimal amount) {
        return balance.compareTo(amount) >= 0;
    }

    public static String getDefaultMCCByAmountType(AmountTypeEnum amountType) {
        return switch (amountType) {
            case FOOD -> "5411";
            case MEAL -> "5811";
            case CASH -> "7011";
        };
    }

    public static boolean isValidMCCForAmountType(String mcc, AmountTypeEnum amountType) {
        int mccCode = Integer.parseInt(mcc);
        return switch (amountType) {
            case FOOD -> 5411 == mccCode || mccCode == 5412;
            case MEAL -> 5811 == mccCode || mccCode == 5812;
            case CASH -> mccCode != 5411 && mccCode != 5412 && mccCode != 5811 && mccCode != 5812;
        };
    }

    public static AmountTypeEnum findAmountTypeByMerchant(String merchant) {
        return ApplicationConstants.merchantMccMap.entrySet().stream()
                .filter(entry -> merchant.toUpperCase().contains(entry.getKey().toUpperCase()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(AmountTypeEnum.CASH);
    }
}
