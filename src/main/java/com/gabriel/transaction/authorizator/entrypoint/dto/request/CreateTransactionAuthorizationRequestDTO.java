package com.gabriel.transaction.authorizator.entrypoint.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gabriel.transaction.authorizator.entrypoint.annotation.validation.ValidMCC;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CreateTransactionAuthorizationRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 6971135005746941877L;

    @NotNull
    @Schema(description = "ID of the account", example = "e7b8a9d2-4c3b-4f8e-9b8e-1a2b3c4d5e6f")
    private UUID accountId;

    @NotNull
    @Schema(description = "Total amount of the transaction", example = "500.55")
    private BigDecimal totalAmount;

    @NotBlank
    @ValidMCC
    @Schema(description = "MCC code", example = "5411")
    private String mcc;

    @NotBlank
    @Schema(description = "Merchant name", example = "UBER TRIP")
    private String merchant;

}
