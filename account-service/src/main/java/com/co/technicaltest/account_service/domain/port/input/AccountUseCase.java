package com.co.technicaltest.account_service.domain.port.input;

import com.co.technicaltest.account_service.application.dto.AccountRequestDTO;
import com.co.technicaltest.account_service.application.dto.AccountResponseDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Interface account use case.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public interface AccountUseCase {


    /**
     * Method for find all accounts.
     *
     * @return List<AccountResponseDTO> account list to response dto
     */
    List<AccountResponseDTO> getAllAccounts();

    /**
     * Method for find account by username
     *
     * @param accountNumber the accaunt number.
     * @return Optional<AccountResponseDTO> ooptional account discount for the response
     */
    Optional<AccountResponseDTO> findAccountByAccountNumber(String accountNumber);

    /**
     * Method for save account
     *
     * @param account data transafer objet account request for save.
     * @return Optional<AccountResponseDTO> optional account discount for the response
     */
    Optional<AccountResponseDTO> saveAccount(AccountRequestDTO account);

    /**
     * Method for update account
     *
     * @param account   data transafer objet account request for update.
     * @param accountId numeric id for intentificacion account.
     * @return Optional<AccountResponseDTO> optional account discount for the response.
     */
    Optional<AccountResponseDTO> updateAccount(AccountRequestDTO account, Long accountId);

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
     * @param accountNumber  numeric id for intentificacion account.
     */
    void updateBalanceAccount(String accountNumber, BigDecimal newBalance);


}
