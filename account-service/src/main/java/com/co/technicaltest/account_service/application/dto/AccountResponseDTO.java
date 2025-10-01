package com.co.technicaltest.account_service.application.dto;

import com.co.technicaltest.account_service.domain.enums.BankAccountType;
import lombok.Builder;

import java.math.BigDecimal;

/**
 * DTO that has the response information from account.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Builder
public record AccountResponseDTO(Long id, String accountNumber, BankAccountType accountType,
                                 BigDecimal balance, Boolean status) {
}
