package com.co.technicaltest.account_service.application.usecase;

import com.co.technicaltest.account_service.application.dto.AccountRequestDTO;
import com.co.technicaltest.account_service.application.dto.AccountResponseDTO;
import com.co.technicaltest.account_service.application.mapper.AccountDTOMapper;
import com.co.technicaltest.account_service.application.mapper.AccountEventMapper;
import com.co.technicaltest.account_service.domain.exception.AccountNotFoundException;
import com.co.technicaltest.account_service.domain.model.Account;
import com.co.technicaltest.account_service.domain.port.input.AccountUseCase;
import com.co.technicaltest.account_service.domain.port.output.AccountRepositoryPort;
import com.co.technicaltest.account_service.domain.port.output.messaging.publisher.AccountMessagePublisher;
import com.co.technicaltest.account_service.infrastructure.shared.enums.ExceptionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Use case port implemetation for Customer.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Slf4j
@Service
public class AccountService implements AccountUseCase {


    private final AccountRepositoryPort repository;

    private final AccountDTOMapper mapper;

    private final AccountMessagePublisher accountMessagePublisher;

    private final AccountEventMapper accountEventMapper;


    public AccountService(AccountRepositoryPort repository,
                          AccountDTOMapper mapper,
                          AccountMessagePublisher accountMessagePublisher,
                          AccountEventMapper accountEventMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.accountMessagePublisher = accountMessagePublisher;
        this.accountEventMapper = accountEventMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public List<AccountResponseDTO> getAllAccounts() {

        log.info("Metodo: {}, para obtener todos las cuentas",
                "[getAllAccounts]");

        return this.repository.getAllAccounts().stream()
                .map(this.mapper::toAccountResponseDTO)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<AccountResponseDTO> findAccountByAccountNumber(String accountNumber) {

        log.info("Metodo: {}, para obtener cuenta por numero de cuenta: {}",
                "[findAccountByAccountNumber]", accountNumber);

        return Optional.ofNullable(this.repository.findAccountByAccountNumber(accountNumber)
                .map(this.mapper::toAccountResponseDTO)
                .orElseThrow(() -> new AccountNotFoundException(
                        String.format(ExceptionMessage.ACCOUNT_NUMBER_NOT_FOUND.getMessage(), accountNumber))));

    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Optional<AccountResponseDTO> saveAccount(AccountRequestDTO account) {

        if (Objects.isNull(account)) return Optional.empty();

        UUID customerId = UUID.fromString
                (account.customerId());


        Account accountCreated =
                this.mapper.toAccountRequest(account);

        accountCreated.balanceIsLessThanZero();
        accountCreated.setBankAccountNumber();

        log.info("Metodo: {}, para guardar cuenta con numero generado: {}",
                "[saveAccount]", accountCreated.getAccountNumber());

        return this.repository.saveAccount(accountCreated, customerId)
                .map(saved -> {
                    this.accountMessagePublisher.publish(this.accountEventMapper.toCreateAccountEvent(saved),
                            customerId);
                    return this.mapper.toAccountResponseDTO(saved);
                });
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Optional<AccountResponseDTO> updateAccount(AccountRequestDTO account, Long accountId) {

        log.info("Metodo: {}, para actualizar cuenta con id: {}",
                "[updateAccount]", accountId);

        if (Objects.isNull(account)) return Optional.empty();

        Account accountUpdated =
                this.mapper.toAccountRequest(account);

        accountUpdated.balanceIsLessThanZero();

        UUID customerId = UUID.fromString
                (account.customerId());

        return this.repository.updateAccount(accountUpdated, accountId)
                .map(updated -> {
                    this.accountMessagePublisher.publish(this.accountEventMapper.toUpdateAccountEvent(updated),
                            customerId);
                    return this.mapper.toAccountResponseDTO(updated);
                });
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void deleteAccount(Long accountId) {

        log.info("Metodo: {}, para eliminar cuenta por id: {}",
                "[deleteAccount]", accountId);

        Account account = this.repository.findAccountByAccountId(accountId)
                .orElseThrow(() -> new AccountNotFoundException(
                        String.format(ExceptionMessage.ACCOUNT_NUMBER_NOT_FOUND.getMessage(), accountId.toString())));

        this.repository.deleteAccount(accountId);

        this.accountMessagePublisher.publish(this.accountEventMapper.toDeleteAccountEvent(account),
                account.getCustomer().getCustomerId());

    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void updateBalanceAccount(String accountNumber, BigDecimal newBalance) {

        log.info("Metodo: {}, para actualizar saldo en cuenta numero: {}",
                "[updateBalanceAccount]", accountNumber);

        Account account = this.repository.findAccountByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(
                        String.format(ExceptionMessage.ACCOUNT_NUMBER_NOT_FOUND.getMessage(), accountNumber)));

        account.setBalance(newBalance);
        account.balanceIsLessThanZero();


        this.repository.updateBalanceAccount(accountNumber, newBalance);

        this.accountMessagePublisher.publish(this.accountEventMapper.toUpdateAccountEvent(account),
                account.getCustomer().getCustomerId());

        //TODO: Logica para publicar evento al servicio transacion que esta se realizo correctamente o hubo fallo
    }
}
