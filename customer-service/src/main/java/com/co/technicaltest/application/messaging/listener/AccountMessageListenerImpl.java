package com.co.technicaltest.application.messaging.listener;

import com.co.technicaltest.application.exception.BankAccountException;
import com.co.technicaltest.domain.model.Account;
import com.co.technicaltest.domain.port.input.messaging.listener.AccountMessageListener;
import com.co.technicaltest.domain.port.output.AccountRepositoryPort;
import com.co.technicaltest.domain.port.output.CustomerRepositoryPort;
import com.co.technicaltest.infrastructure.shared.enums.ExceptionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Implementation to account listener event .
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Slf4j
@Service
public class AccountMessageListenerImpl implements AccountMessageListener {


    private final AccountRepositoryPort accountRepository;

    private final CustomerRepositoryPort customerRepository;

    public AccountMessageListenerImpl(AccountRepositoryPort accountRepository,
                                      CustomerRepositoryPort customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void associateWithCustomer(Account account, UUID customerId) {

        log.info("Metodo: {}, para guarda la cuenta: {}, asociada al cliente con id: {}",
                "[associateWithCustomer]", account.toString(), customerId);

        Account result = this.accountRepository.saveAccount(account, customerId);

        if (result == null) {
            log.error("No se puedo asociar cuenta con id: {}, a usuario con id: {}", account.getAccountId(), customerId);

            throw new BankAccountException(
                    String.format(
                            ExceptionMessage.ACCOUNT_ASSOCIATED_ERROR.getMessage()
                            , account.getAccountId(), customerId));
        }

        log.info("Se creo cuenta en base de datos con id: {} ," +
                " asociada al cliente con id: {}", account.toString(), customerId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disassociateWithCustomer(Account account, UUID customerId) {

        log.info("Metodo: {}, para eliminar la cuenta: {}, asociada al cliente con id: {}",
                "[disassociateWithCustomer]", account.getAccountId(), customerId);

        this.customerRepository.deleteAccountFromCustomer(customerId, account.getAccountId());

    }
}
