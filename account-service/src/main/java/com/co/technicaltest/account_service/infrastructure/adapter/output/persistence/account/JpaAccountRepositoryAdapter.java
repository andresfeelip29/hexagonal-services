package com.co.technicaltest.account_service.infrastructure.adapter.output.persistence.account;

import com.co.technicaltest.account_service.domain.exception.AccountNotFoundException;
import com.co.technicaltest.account_service.domain.exception.CustomerNotFoundException;
import com.co.technicaltest.account_service.domain.model.Account;
import com.co.technicaltest.account_service.domain.model.Customer;
import com.co.technicaltest.account_service.domain.port.output.AccountRepositoryPort;
import com.co.technicaltest.account_service.domain.port.output.CustomerRepositoryPort;
import com.co.technicaltest.account_service.infrastructure.adapter.output.persistence.customer.SpringDataCustomerRespository;
import com.co.technicaltest.account_service.infrastructure.adapter.output.persistence.entities.AccountEntity;
import com.co.technicaltest.account_service.infrastructure.adapter.output.persistence.entities.CustomerEntity;
import com.co.technicaltest.account_service.infrastructure.mapper.AccountMapper;
import com.co.technicaltest.account_service.infrastructure.mapper.CustomerMapper;
import com.co.technicaltest.account_service.infrastructure.shared.enums.ExceptionMessage;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository adapter implemetation for Account.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Repository
public class JpaAccountRepositoryAdapter implements AccountRepositoryPort {


    private final SpringDataAccountRespository repository;

    private final SpringDataCustomerRespository customerRepository;

    private final AccountMapper mapper;


    public JpaAccountRepositoryAdapter(SpringDataAccountRespository repository,
                                       SpringDataCustomerRespository customerRepository,
                                       AccountMapper mapper) {
        this.repository = repository;
        this.customerRepository = customerRepository;
        this.mapper = mapper;

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Account> getAllAccounts() {
        return this.repository.findAll()
                .stream()
                .map(this.mapper::accountEntityToAccount)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Account> findAccountByAccountId(Long accountId) {

        AccountEntity accountEntity =
                this.repository.findById(accountId)
                        .orElseThrow(() -> new AccountNotFoundException(
                                String.format(ExceptionMessage.ACCOUNT_NOT_FOUND.getMessage(), accountId)));

        if (Objects.isNull(accountEntity)) return Optional.empty();

        return Optional.of(this.mapper.accountEntityToAccount(accountEntity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Account> findAccountByAccountNumber(String accountNumber) {

        AccountEntity accountEntity =
                this.repository.findAccountEntitiesByAccountNumber(accountNumber)
                        .orElseThrow(() -> new AccountNotFoundException(
                                String.format(ExceptionMessage.ACCOUNT_NUMBER_NOT_FOUND.getMessage(), accountNumber)));

        if (Objects.isNull(accountEntity)) return Optional.empty();

        return Optional.of(this.mapper.accountEntityToAccount(accountEntity));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Account> saveAccount(Account account, UUID customerId) {

        CustomerEntity customer = this.customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format(ExceptionMessage.CUSTOMER_NOT_FOUND.getMessage(), customerId.toString())));

        AccountEntity accountEntity =
                this.mapper.accountToAccountEntity(account);

        accountEntity.setCustomer(customer);

        this.repository.save(accountEntity);
        return Optional.of(this.mapper.accountEntityToAccount(accountEntity));

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Account> updateAccount(Account account, Long accountId) {

        AccountEntity accountEntity =
                this.repository.findById(accountId)
                        .orElseThrow(() -> new AccountNotFoundException(
                                String.format(ExceptionMessage.ACCOUNT_NOT_FOUND.getMessage(), accountId)));

        AccountEntity accountEntityUpdated =
                this.mapper.updateAccountEntity(accountEntity, account);

        this.repository.save(accountEntityUpdated);
        return Optional.of(this.mapper.accountEntityToAccount(accountEntityUpdated));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAccount(Long accountId) {

        AccountEntity accountEntity =
                this.repository.findById(accountId)
                        .orElseThrow(() -> new AccountNotFoundException(
                                String.format(ExceptionMessage.ACCOUNT_NOT_FOUND.getMessage(), accountId)));

        this.repository.delete(accountEntity);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBalanceAccount(String accountNumber, BigDecimal newBalance) {

        AccountEntity accountEntity =
                this.repository.findAccountEntitiesByAccountNumber(accountNumber)
                        .orElseThrow(() -> new AccountNotFoundException(
                                String.format(ExceptionMessage.ACCOUNT_NUMBER_NOT_FOUND.getMessage(), accountNumber)));

        accountEntity.setBalance(newBalance);
        this.repository.save(accountEntity);
    }
}
