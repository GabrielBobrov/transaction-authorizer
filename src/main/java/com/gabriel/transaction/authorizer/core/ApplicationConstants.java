package com.gabriel.transaction.authorizer.core;

import com.gabriel.transaction.authorizer.core.enums.AmountTypeEnum;

import java.util.HashMap;
import java.util.Map;

public class ApplicationConstants {

    public static final String MCC_FOOD = "5411";
    public static final String MCC_MEAL = "5811";
    public static final String MCC_CASH = "7011";
    public static final String MCC_FOOD_ALTERNATIVE = "5412";
    public static final String MCC_MEAL_ALTERNATIVE = "5812";
    public static final Map<String, AmountTypeEnum> merchantMccMap = new HashMap<>();

    static {
        merchantMccMap.put("TRIP", AmountTypeEnum.CASH);
        merchantMccMap.put("PAG", AmountTypeEnum.CASH);
        merchantMccMap.put("PAGAMENTO", AmountTypeEnum.CASH);
        merchantMccMap.put("PAGTO", AmountTypeEnum.CASH);
        merchantMccMap.put("PICPAY", AmountTypeEnum.CASH);
        merchantMccMap.put("BANCO", AmountTypeEnum.CASH);
        merchantMccMap.put("FINANCIAMENTO", AmountTypeEnum.CASH);

        merchantMccMap.put("EATS", AmountTypeEnum.FOOD);
        merchantMccMap.put("COMIDA", AmountTypeEnum.FOOD);
        merchantMccMap.put("FOOD", AmountTypeEnum.FOOD);
        merchantMccMap.put("MERCADO", AmountTypeEnum.FOOD);
        merchantMccMap.put("MARKET", AmountTypeEnum.FOOD);
        merchantMccMap.put("SUPERMERCADO", AmountTypeEnum.FOOD);
        merchantMccMap.put("MERCADINHO", AmountTypeEnum.FOOD);
        merchantMccMap.put("MERCADÃO", AmountTypeEnum.FOOD);
        merchantMccMap.put("PADARIA", AmountTypeEnum.FOOD);
        merchantMccMap.put("RESTAURANTE", AmountTypeEnum.FOOD);

        merchantMccMap.put("MEATZ", AmountTypeEnum.MEAL);
        merchantMccMap.put("ALIMENTAÇÃO", AmountTypeEnum.MEAL);
        merchantMccMap.put("ALIMENTACAO", AmountTypeEnum.MEAL);
        merchantMccMap.put("LUNCH", AmountTypeEnum.MEAL);
        merchantMccMap.put("DINNER", AmountTypeEnum.MEAL);

    }
}
