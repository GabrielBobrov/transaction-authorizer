package com.gabriel.transaction.authorizator.core.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionAuthorizationModel {

    private UUID transactionId;

    private UUID accountId;

    private BigDecimal totalAmount;

    private String mcc;

    private String merchant;
}
