package com.co.technicaltest.infrastructure.adapter.output.persistence.account;

import com.co.technicaltest.domain.model.Account;
import com.co.technicaltest.domain.port.output.AccountRepositoryPort;
import com.co.technicaltest.infrastructure.adapter.output.persistence.entities.AccountEntity;
import com.co.technicaltest.infrastructure.exception.AccountNotFoundException;
import com.co.technicaltest.infrastructure.mapper.AccountMapper;
import com.co.technicaltest.infrastructure.shared.enums.ExceptionMessage;
import org.springframework.stereotype.Repository;

/**
 * Repository adapter implemetation for Account.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Repository
public class JpaAccountRepositoryAdapter implements AccountRepositoryPort {


    private final SpringDataAccountRepository repository;

    private final AccountMapper accountMapper;

    public JpaAccountRepositoryAdapter(SpringDataAccountRepository repository,
                                       AccountMapper accountMapper) {
        this.repository = repository;
        this.accountMapper = accountMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account findAccountById(Long id) {
        return this.repository.findById(id)
                .map(this.accountMapper::accountEntityToAccount)
                .orElseThrow(() -> new AccountNotFoundException(String.format(ExceptionMessage.ACCOUNT_NOT_FOUND.getMessage(), id)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account saveAccount(Account account) {
        AccountEntity accountEntity =
                this.accountMapper.accountToAccountEntity(account);
        this.repository.save(accountEntity);
        return account;
    }
}
