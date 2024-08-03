package com.gabriel.transaction.authorizator.entrypoint.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Schema(description = "Model containing the result of a transaction authorization")
public class CreateTransactionAuthorizationResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 6971135005746941877L;

    @Schema(description = "Response code", example = "00")
    private String code;
}