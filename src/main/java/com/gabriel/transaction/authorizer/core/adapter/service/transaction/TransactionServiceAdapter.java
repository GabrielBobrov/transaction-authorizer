package com.gabriel.transaction.authorizer.core.adapter.service.transaction;

import com.gabriel.transaction.authorizer.core.enums.AmountTypeEnum;
import com.gabriel.transaction.authorizer.core.exception.InsufficientTotalAmountException;
import com.gabriel.transaction.authorizer.core.exception.InvalidMerchantException;
import com.gabriel.transaction.authorizer.core.exception.InvalidTotalAmountException;
import com.gabriel.transaction.authorizer.core.model.CreateTransactionAuthorizationModel;
import com.gabriel.transaction.authorizer.core.model.CreateTransactionAuthorizationResultModel;
import com.gabriel.transaction.authorizer.core.ports.in.service.account.IAccountAmountServicePort;
import com.gabriel.transaction.authorizer.core.ports.in.service.account.IAccountServicePort;
import com.gabriel.transaction.authorizer.core.ports.in.service.transaction.ITransactionServicePort;
import com.gabriel.transaction.authorizer.core.ports.out.repository.transaction.ITransactionRepositoryPort;
import com.gabriel.transaction.authorizer.infrastructure.entity.account.AccountEntity;
import com.gabriel.transaction.authorizer.infrastructure.entity.transaction.TransactionEntity;
import com.gabriel.transaction.authorizer.infrastructure.entity.transaction.enums.TransactionStatusEnum;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.gabriel.transaction.authorizer.core.util.AmountUtil.findAmountTypeByMerchant;
import static com.gabriel.transaction.authorizer.core.util.AmountUtil.getDefaultMCCByAmountType;
import static com.gabriel.transaction.authorizer.core.util.AmountUtil.isValidMCCForAmountType;

/**
 * Adapter for the transaction service.
 */
@Slf4j
@Service
@AllArgsConstructor
public class TransactionServiceAdapter implements ITransactionServicePort {

    private final ITransactionRepositoryPort transactionRepositoryPort;
    private final IAccountServicePort accountServicePort;
    private final List<IAccountAmountServicePort> accountAmountStrategiesServicePorts;

    /**
     * Authorizes a transaction based on the provided model.
     *
     * @param createTransactionAuthorizationModel the model containing transaction details
     * @return the result of the transaction authorization
     * @throws InsufficientTotalAmountException if there is insufficient balance
     */
    @Override
    @Transactional(dontRollbackOn = InsufficientTotalAmountException.class)
    public synchronized CreateTransactionAuthorizationResultModel authorizeTransaction(CreateTransactionAuthorizationModel createTransactionAuthorizationModel) {
        log.info("Class {} method authorizeTransaction", this.getClass().getName());
        log.info("CreateTransactionAuthorizationModel: {}", createTransactionAuthorizationModel);

        AccountEntity account = accountServicePort.findById(createTransactionAuthorizationModel.getAccountId());
        TransactionEntity transaction = transactionRepositoryPort.findByIdAndStatusAndAccount(createTransactionAuthorizationModel.getTransactionId(), TransactionStatusEnum.PENDING_AUTHORIZATION, account);

        validateTransaction(createTransactionAuthorizationModel, transaction);

        AmountTypeEnum amountTypeByMerchant = findAmountTypeByMerchant(createTransactionAuthorizationModel.getMerchant());
        boolean isMccValid = isValidMCCForAmountType(createTransactionAuthorizationModel.getMcc(), amountTypeByMerchant);

        if (Objects.equals(isMccValid, Boolean.FALSE) ||
                !Objects.equals(transaction.getMcc(), createTransactionAuthorizationModel.getMcc())) {
            String mccByAmountType = getDefaultMCCByAmountType(amountTypeByMerchant);
            transaction.setMcc(mccByAmountType);
        }

        accountAmountStrategiesServicePorts.stream()
                .filter(accountAmountServicePort -> accountAmountServicePort.getAmountType().equals(amountTypeByMerchant))
                .findFirst()
                .orElseThrow()
                .updateAmount(account, createTransactionAuthorizationModel.getTotalAmount(), transaction);

        return CreateTransactionAuthorizationResultModel.builder()
                .code("00")
                .build();
    }

    /**
     * Validates the transaction details.
     *
     * @param createTransactionAuthorizationModel the model containing transaction details
     * @param transaction                         the transaction entity to validate against
     * @throws InvalidTotalAmountException if the total amount is invalid
     * @throws InvalidMerchantException    if the merchant is invalid
     */
    private void validateTransaction(CreateTransactionAuthorizationModel createTransactionAuthorizationModel, TransactionEntity transaction) {
        if (!Objects.equals(createTransactionAuthorizationModel.getTotalAmount(), transaction.getTotalAmount()))
            throw new InvalidTotalAmountException("07");

        if (!Objects.equals(createTransactionAuthorizationModel.getMerchant(), transaction.getMerchant()))
            throw new InvalidMerchantException("07");
    }
}