package com.co.technicaltest.account_service.infrastructure.adapter.output.persistence.account;

import com.co.technicaltest.account_service.domain.model.Account;
import com.co.technicaltest.account_service.domain.port.output.AccountRepositoryPort;
import com.co.technicaltest.account_service.infrastructure.mapper.AccountMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Repository adapter implemetation for Account.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Repository
public class JpaAccountRepositoryAdapter implements AccountRepositoryPort {


    private final SpringDataAccountRespository repository;

    private final AccountMapper mapper;



    public JpaAccountRepositoryAdapter(SpringDataAccountRespository repository, AccountMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Account> getAllAccounts() {
        return List.of();
    }

    @Override
    public Optional<Account> findAccountByAccountNumber(String accountNumber) {
        return Optional.empty();
    }

    @Override
    public Optional<Account> saveAccount(Account account) {
        return Optional.empty();
    }

    @Override
    public Optional<Account> updateAccount(Account account, Long accountId) {
        return Optional.empty();
    }

    @Override
    public void deleteAccount(Long accountId) {

    }

    @Override
    public void updateBalanceAccount(Long accountId, BigDecimal newBalance) {

    }
}
