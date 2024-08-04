package com.gabriel.transaction.authorizer.entrypoint.disassembler;

import com.gabriel.transaction.authorizer.core.model.CreateTransactionAuthorizationModel;
import com.gabriel.transaction.authorizer.entrypoint.dto.request.CreateTransactionAuthorizationRequestDTO;
import com.gabriel.transaction.authorizer.entrypoint.mapper.ITransactionAuthorizationEntrypointMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class TransactionAuthorizationDisassembler {
    private final ITransactionAuthorizationEntrypointMapper transactionAuthorizationEntrypointMapper;

    public CreateTransactionAuthorizationModel toModel(CreateTransactionAuthorizationRequestDTO createTransactionAuthorizationRequestDTO, UUID transactionId) {

        CreateTransactionAuthorizationModel createTransactionAuthorizationModel = transactionAuthorizationEntrypointMapper.fromCreateTransactionAuthorizationRequestDTOToCreateTransactionAuthorizationModel(createTransactionAuthorizationRequestDTO, transactionId);
        log.info("CreateTransactionAuthorizationModel {}", createTransactionAuthorizationModel);

        return createTransactionAuthorizationModel;
    }
}