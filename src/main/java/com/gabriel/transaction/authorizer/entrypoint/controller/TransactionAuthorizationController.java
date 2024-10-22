package com.gabriel.transaction.authorizer.entrypoint.controller;


import com.gabriel.transaction.authorizer.core.model.CreateTransactionAuthorizationModel;
import com.gabriel.transaction.authorizer.core.model.CreateTransactionAuthorizationResultModel;
import com.gabriel.transaction.authorizer.core.ports.in.service.transaction.ITransactionServicePort;
import com.gabriel.transaction.authorizer.entrypoint.UrlConstant;
import com.gabriel.transaction.authorizer.entrypoint.disassembler.TransactionAuthorizationDisassembler;
import com.gabriel.transaction.authorizer.entrypoint.assembler.TransactionAuthorizationAssembler;
import com.gabriel.transaction.authorizer.entrypoint.dto.request.CreateTransactionAuthorizationRequestDTO;
import com.gabriel.transaction.authorizer.entrypoint.dto.response.CreateTransactionAuthorizationResponseDTO;
import com.gabriel.transaction.authorizer.entrypoint.openapi.controller.TransactionAuthorizationControllerOpenApi;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = UrlConstant.TRANSACTION_AUTHORIZATION_URI)
public class TransactionAuthorizationController implements TransactionAuthorizationControllerOpenApi {

    private final ITransactionServicePort transferServicePort;
    private final TransactionAuthorizationDisassembler transactionAuthorizationDisassembler;
    private final TransactionAuthorizationAssembler transactionAuthorizationAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CreateTransactionAuthorizationResponseDTO createTransactionAuthorization(@RequestBody @Valid CreateTransactionAuthorizationRequestDTO createTransactionAuthorizationRequestDTO,
                                                                                    @PathVariable UUID id) {
        log.info("Class {} method createTransactionAuthorization", this.getClass().getName());
        log.info("CreateTransactionAuthorizationRequestDTO {}", createTransactionAuthorizationRequestDTO);

        CreateTransactionAuthorizationModel createTransactionAuthorizationModel = transactionAuthorizationDisassembler.toModel(createTransactionAuthorizationRequestDTO, id);

        CreateTransactionAuthorizationResultModel createTransactionAuthorizationResultModel = transferServicePort.authorizeTransaction(createTransactionAuthorizationModel);
        log.info("CreateTransactionAuthorizationResultModel {}", createTransactionAuthorizationResultModel);

        return transactionAuthorizationAssembler.toResponse(createTransactionAuthorizationResultModel);
    }

}



