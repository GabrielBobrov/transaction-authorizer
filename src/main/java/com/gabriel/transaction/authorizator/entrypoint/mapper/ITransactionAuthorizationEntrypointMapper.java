package com.gabriel.transaction.authorizator.entrypoint.mapper;


import com.gabriel.transaction.authorizator.core.model.CreateTransactionAuthorizationModel;
import com.gabriel.transaction.authorizator.core.model.CreateTransactionAuthorizationResultModel;
import com.gabriel.transaction.authorizator.entrypoint.dto.request.CreateTransactionAuthorizationRequestDTO;
import com.gabriel.transaction.authorizator.entrypoint.dto.response.CreateTransactionAuthorizationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ITransactionAuthorizationEntrypointMapper {
    CreateTransactionAuthorizationModel fromCreateTransactionAuthorizationRequestDTOToCreateTransactionAuthorizationModel(CreateTransactionAuthorizationRequestDTO createTransactionAuthorizationRequestDTO, UUID transactionId);

    CreateTransactionAuthorizationResponseDTO fromCreateTransactionAuthorizationResultModelToCreateTransactionAuthorizationResponseDTO(CreateTransactionAuthorizationResultModel createTransactionAuthorizationModel);
}
