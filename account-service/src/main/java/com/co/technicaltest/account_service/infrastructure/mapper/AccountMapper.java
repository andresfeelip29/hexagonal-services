package com.co.technicaltest.account_service.infrastructure.mapper;

import com.co.technicaltest.account_service.domain.model.Account;
import com.co.technicaltest.account_service.infrastructure.adapter.output.persistence.entities.AccountEntity;
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


    private final CustomerMapper customerMapper;

    public AccountMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public Account accountEntityToAccount(AccountEntity accountEntity) {
        return Account.builder()
                .accountId(accountEntity.getId())
                .accountNumber(accountEntity.getAccountNumber())
                .accountType(accountEntity.getAccountType())
                .balance(accountEntity.getBalance())
                .status(accountEntity.getStatus())
                .customer(this.customerMapper.toCustomer(accountEntity.getCustomer()))
                .build();
    }

    public AccountEntity accountToAccountEntity(Account account) {
        return AccountEntity.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .balance(account.getBalance())
                .status(account.getStatus())
                .build();
    }

    public AccountEntity updateAccountEntity(AccountEntity accountEntity, Account account) {
        accountEntity.setAccountType(account.getAccountType());
        accountEntity.setBalance(account.getBalance());
        accountEntity.setStatus(account.getStatus());
        return accountEntity;
    }

    public List<Account> accountEntityToAccountList(List<AccountEntity> accountEntityList) {
        if (Objects.isNull(accountEntityList)) return Collections.emptyList();
        return accountEntityList.stream()
                .map(this::accountEntityToAccount)
                .toList();
    }
}
