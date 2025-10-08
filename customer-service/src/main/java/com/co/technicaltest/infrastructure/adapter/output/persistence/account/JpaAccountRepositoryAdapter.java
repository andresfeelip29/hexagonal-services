package com.co.technicaltest.infrastructure.adapter.output.persistence.account;

import com.co.technicaltest.application.exception.CustomerNotFoundException;
import com.co.technicaltest.domain.model.Account;
import com.co.technicaltest.domain.port.output.AccountRepositoryPort;
import com.co.technicaltest.infrastructure.adapter.output.persistence.customer.SpringDataCustomerRespository;
import com.co.technicaltest.infrastructure.adapter.output.persistence.entities.AccountEntity;
import com.co.technicaltest.infrastructure.adapter.output.persistence.entities.CustomerEntity;
import com.co.technicaltest.infrastructure.exception.AccountNotFoundException;
import com.co.technicaltest.infrastructure.mapper.AccountMapper;
import com.co.technicaltest.infrastructure.shared.enums.ExceptionMessage;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository adapter implemetation for Account.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Repository
public class JpaAccountRepositoryAdapter implements AccountRepositoryPort {


    private final SpringDataAccountRepository accountRepository;

    private final SpringDataCustomerRespository customerRespository;

    private final AccountMapper accountMapper;

    public JpaAccountRepositoryAdapter(SpringDataAccountRepository accountRepository,
                                       SpringDataCustomerRespository customerRespository,
                                       AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.customerRespository = customerRespository;
        this.accountMapper = accountMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account findAccountById(Long id) {
        return this.accountRepository.findById(id)
                .map(this.accountMapper::accountEntityToAccount)
                .orElseThrow(() -> new AccountNotFoundException(String.format(ExceptionMessage.ACCOUNT_NOT_FOUND.getMessage(), id)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account saveAccount(Account account, UUID customerId) {

        CustomerEntity customerEntity =
                this.customerRespository.findCustomerEntitiesByCustomerId(customerId)
                        .orElseThrow(() -> new CustomerNotFoundException(
                                String.format(ExceptionMessage.USERNAME_NOT_FOUND.getMessage(), customerId)));

        AccountEntity accountEntity =
                this.accountMapper.accountToAccountEntity(account);

        accountEntity.setCustomer(customerEntity);

        this.accountRepository.save(accountEntity);
        return account;
    }
}
