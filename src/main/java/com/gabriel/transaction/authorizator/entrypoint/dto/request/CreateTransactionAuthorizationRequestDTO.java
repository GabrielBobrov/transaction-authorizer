package com.gabriel.transaction.authorizator.entrypoint.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gabriel.transaction.authorizator.entrypoint.annotation.validation.ValidMCC;
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
    private UUID accountId;

    @NotNull
    private BigDecimal totalAmount;

    @NotBlank
    @ValidMCC
    private String mcc;

    @NotBlank
    private String merchant;

}
