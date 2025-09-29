package com.co.technicaltest.infrastructure.mapper;

import com.co.technicaltest.domain.model.Account;
import com.co.technicaltest.infrastructure.adapter.output.persistence.entities.AccountEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class to mapper entity information and persist it in a database.
 *
 * @author andres on 2025/09/28.
 * @version 1.0.0
 */
@Component
public class AccountMapper {


    public Account accountEntityToAccount(AccountEntity accountEntity) {
        return Account.builder()
                .accountId(accountEntity.getId())
                .accountNumber(accountEntity.getAccountNumber())
                .accountType(accountEntity.getAccountType())
                .balance(accountEntity.getBalance())
                .status(accountEntity.getStatus())
                .build();
    }

    public AccountEntity accountToAccountEntity(Account account) {
        return AccountEntity.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .balance(account.getBalance())
                .status(account.getStatus())
                .id(account.getAccountId())
                .build();
    }

    public List<Account> accountEntityToAccountList(List<AccountEntity> accountEntityList) {
        if (Objects.isNull(accountEntityList)) return Collections.emptyList();
        return accountEntityList.stream()
                .map(this::accountEntityToAccount)
                .toList();
    }
}
