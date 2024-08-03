package com.gabriel.transaction.authorizer.entrypoint.openapi.controller;

import com.gabriel.transaction.authorizer.entrypoint.dto.request.CreateTransactionAuthorizationRequestDTO;
import com.gabriel.transaction.authorizer.entrypoint.dto.response.CreateTransactionAuthorizationResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Tag(name = "Transaction Authorization", description = "Endpoints for managing transactions authorizations")
public interface TransactionAuthorizationControllerOpenApi {

    @Operation(summary = "Authorize a transaction", description = "Authorize a transaction based on the provided model")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction authorized successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateTransactionAuthorizationResponseDTO.class)))
    })
    CreateTransactionAuthorizationResponseDTO createTransactionAuthorization(@Parameter(description = "Model containing transaction details", required = true) @RequestBody @Valid CreateTransactionAuthorizationRequestDTO createTransactionAuthorizationRequestDTO,
                                                                             @Parameter(description = "Account id", example = "a1b2c3d4-5e6f-7a8b-9c0d-1e2f3a4b5c6d", required = true) UUID id);
}