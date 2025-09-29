package com.co.technicaltest.domain.port.output;

import com.co.technicaltest.domain.model.Account;



/**
 * Repositorio port for account.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public interface AccountRepositoryPort {

    /**
     * Method to obtain the account by id.
     *
     * @param id account id.
     * @return Account domain data for account
     */
    Account findAccountById(Long id);

    /**
     * Method to save the account.
     *
     * @param account domain data for account.
     * @return Account domain data for account
     */
    Account saveAccount(Account account);
}
