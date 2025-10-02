package com.co.technicaltest.account_service.application.mapper;


import com.co.technicaltest.account_service.application.dto.AccountRequestDTO;
import com.co.technicaltest.account_service.application.dto.AccountResponseDTO;
import com.co.technicaltest.account_service.domain.model.Account;
import org.springframework.stereotype.Component;

/**
 * Class to mapper DTO from domain objetc.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Component
public class AccountDTOMapper {


    public AccountResponseDTO toAccountResponseDTO(Account account){
        return AccountResponseDTO.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .id(account.getAccountId())
                .balance(account.getBalance())
                .status(account.getStatus())
                .build();
    }

    public Account toAccountRequest(AccountRequestDTO accountRequestDTO){
        return Account.builder()
                .accountType(accountRequestDTO.accountType())
                .balance(accountRequestDTO.balance())
                .status(accountRequestDTO.status())
                .build();

    }
}
