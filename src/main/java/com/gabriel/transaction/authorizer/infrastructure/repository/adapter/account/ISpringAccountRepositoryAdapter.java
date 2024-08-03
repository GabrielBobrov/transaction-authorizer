package com.gabriel.transaction.authorizer.infrastructure.repository.adapter.account;

import com.gabriel.transaction.authorizer.infrastructure.entity.account.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ISpringAccountRepositoryAdapter extends JpaRepository<AccountEntity, UUID> {

}
