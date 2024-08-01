package com.gabriel.transaction.authorizator.entrypoint.controller;


import com.gabriel.transaction.authorizator.core.model.CreateTransactionAuthorizationModel;
import com.gabriel.transaction.authorizator.core.model.CreateTransactionAuthorizationResultModel;
import com.gabriel.transaction.authorizator.core.ports.in.service.transaction.ITransactionServicePort;
import com.gabriel.transaction.authorizator.entrypoint.UrlConstant;
import com.gabriel.transaction.authorizator.entrypoint.dto.request.CreateTransactionAuthorizationRequestDTO;
import com.gabriel.transaction.authorizator.entrypoint.dto.response.CreateTransactionAuthorizationResponseDTO;
import com.gabriel.transaction.authorizator.entrypoint.mapper.ITransactionAuthorizationEntrypointMapper;
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
public class TransactionAuthorizationController {

    private final ITransactionAuthorizationEntrypointMapper transactionAuthorizationEntrypointMapper;
    private final ITransactionServicePort transferServicePort;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CreateTransactionAuthorizationResponseDTO createTransactionAuthorization(@RequestBody @Valid CreateTransactionAuthorizationRequestDTO createTransactionAuthorizationRequestDTO,
                                                                                    @PathVariable UUID id) {
        log.info("Class {} method createTransactionAuthorization", this.getClass().getName());
        log.info("CreateTransactionAuthorizationRequestDTO {}", createTransactionAuthorizationRequestDTO);

        CreateTransactionAuthorizationModel createTransactionAuthorizationModel = transactionAuthorizationEntrypointMapper.fromCreateTransactionAuthorizationRequestDTOToCreateTransactionAuthorizationModel(createTransactionAuthorizationRequestDTO, id);
        log.info("CreateTransactionAuthorizationModel {}", createTransactionAuthorizationModel);

        CreateTransactionAuthorizationResultModel createTransactionAuthorizationResultModel = transferServicePort.authorizeTransaction(createTransactionAuthorizationModel);
        log.info("CreateTransactionAuthorizationResultModel {}", createTransactionAuthorizationResultModel);

        CreateTransactionAuthorizationResponseDTO createTransactionAuthorizationResponseDTO = transactionAuthorizationEntrypointMapper.fromCreateTransactionAuthorizationResultModelToCreateTransactionAuthorizationResponseDTO(createTransactionAuthorizationResultModel);
        log.info("CreateTransactionAuthorizationResponseDTO {}", createTransactionAuthorizationResponseDTO);
        return createTransactionAuthorizationResponseDTO;
    }

}



