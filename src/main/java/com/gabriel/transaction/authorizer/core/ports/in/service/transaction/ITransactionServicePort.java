package com.gabriel.transaction.authorizer.core.ports.in.service.transaction;


import com.gabriel.transaction.authorizer.core.model.CreateTransactionAuthorizationModel;
import com.gabriel.transaction.authorizer.core.model.CreateTransactionAuthorizationResultModel;

public interface ITransactionServicePort {

    CreateTransactionAuthorizationResultModel authorizeTransaction(CreateTransactionAuthorizationModel createTransactionAuthorizationModel);

}
