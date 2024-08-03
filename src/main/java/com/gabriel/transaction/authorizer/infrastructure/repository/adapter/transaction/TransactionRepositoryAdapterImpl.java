package com.gabriel.transaction.authorizer.infrastructure.repository.adapter.transaction;


import com.gabriel.transaction.authorizer.core.exception.TransactionNotFoundException;
import com.gabriel.transaction.authorizer.core.ports.out.repository.transaction.ITransactionRepositoryPort;
import com.gabriel.transaction.authorizer.infrastructure.entity.account.AccountEntity;
import com.gabriel.transaction.authorizer.infrastructure.entity.transaction.TransactionEntity;
import com.gabriel.transaction.authorizer.infrastructure.entity.transaction.enums.TransactionStatusEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Slf4j
@Component
@AllArgsConstructor
public class TransactionRepositoryAdapterImpl implements ITransactionRepositoryPort {

    private final ISpringTransactionRepositoryAdapter springTransactionRepositoryAdapter;

    @Override
    public TransactionEntity findByIdAndStatusAndAccount(UUID transactionId, TransactionStatusEnum transactionStatusEnum, AccountEntity account) {
        log.info("Class {} method findById", this.getClass().getName());
        log.info("transactionId: {}", transactionId);
        log.info("transactionStatusEnum: {}", transactionStatusEnum);
        log.info("account: {}", account);

        return springTransactionRepositoryAdapter.findByIdAndStatusAndAccount(transactionId, transactionStatusEnum, account)
                .orElseThrow(() -> new TransactionNotFoundException("07"));
    }
}
