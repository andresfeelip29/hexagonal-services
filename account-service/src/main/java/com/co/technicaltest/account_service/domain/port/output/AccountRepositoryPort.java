package com.co.technicaltest.account_service.domain.port.output;


import com.co.technicaltest.account_service.domain.model.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio port for account.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public interface AccountRepositoryPort {

    /**
     * Method for find all accounts.
     *
     * @return List<Account> accounts list data domain
     */
    List<Account> getAllAccounts();

    /**
     * Method for find account by username
     *
     * @param accountNumber the accaunt number.
     * @return Optional<Account> account data domain
     */
    Optional<Account> findAccountByAccountNumber(String accountNumber);

    /**
     * Method for save account
     *
     * @param account data account information for save.
     * @return Optional<Account> customer data domain
     */
    Optional<Account> saveAccount(Account account);

    /**
     * Method for update account
     *
     * @param account   data information.
     * @param accountId numeric id for intentificacion account.
     * @return Optional<Account> customer data domain
     */
    Optional<Account> updateAccount(Account account, Long accountId);

    /**
     * Method for delete account
     *
     * @param accountId numeric id for intentificacion account.
     */
    void deleteAccount(Long accountId);

    /**
     * Method for update balance from domain evento on transactional service
     *
     * @param newBalance new account balance to update.
     * @param accountId  numeric id for intentificacion account.
     */
    void updateBalanceAccount(Long accountId, BigDecimal newBalance);
}
