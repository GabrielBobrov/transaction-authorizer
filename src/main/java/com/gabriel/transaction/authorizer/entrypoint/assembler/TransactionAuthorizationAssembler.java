package com.gabriel.transaction.authorizer.entrypoint.assembler;

import com.gabriel.transaction.authorizer.core.model.CreateTransactionAuthorizationResultModel;
import com.gabriel.transaction.authorizer.entrypoint.dto.response.CreateTransactionAuthorizationResponseDTO;
import com.gabriel.transaction.authorizer.entrypoint.mapper.ITransactionAuthorizationEntrypointMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class TransactionAuthorizationAssembler {
    private final ITransactionAuthorizationEntrypointMapper transactionAuthorizationEntrypointMapper;

    public CreateTransactionAuthorizationResponseDTO toResponse(CreateTransactionAuthorizationResultModel createTransactionAuthorizationResultModel) {

        CreateTransactionAuthorizationResponseDTO createTransactionAuthorizationResponseDTO = transactionAuthorizationEntrypointMapper.fromCreateTransactionAuthorizationResultModelToCreateTransactionAuthorizationResponseDTO(createTransactionAuthorizationResultModel);
        log.info("CreateTransactionAuthorizationResponseDTO {}", createTransactionAuthorizationResponseDTO);

        return createTransactionAuthorizationResponseDTO;
    }
}