package com.gabriel.transaction.authorizator.core.ports.in.service.transaction;


import com.gabriel.transaction.authorizator.core.model.CreateTransactionAuthorizationModel;
import com.gabriel.transaction.authorizator.core.model.CreateTransactionAuthorizationResultModel;

public interface ITransactionServicePort {

    CreateTransactionAuthorizationResultModel authorizeTransaction(CreateTransactionAuthorizationModel createTransactionAuthorizationModel);

}
