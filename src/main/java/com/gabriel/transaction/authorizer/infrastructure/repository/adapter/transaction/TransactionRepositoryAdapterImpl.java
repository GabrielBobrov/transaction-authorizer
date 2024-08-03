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

/**
 * Adapter implementation for transaction repository.
 */
@Slf4j
@Component
@AllArgsConstructor
public class TransactionRepositoryAdapterImpl implements ITransactionRepositoryPort {

    private final ISpringTransactionRepositoryAdapter springTransactionRepositoryAdapter;

    /**
     * Finds a transaction by its ID, status, and associated account.
     *
     * @param transactionId         the UUID of the transaction to find
     * @param transactionStatusEnum the status of the transaction
     * @param account               the account entity associated with the transaction
     * @return the TransactionEntity corresponding to the given ID, status, and account
     * @throws TransactionNotFoundException if the transaction is not found
     */
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