package com.co.technicaltest.application.dto;

import com.co.technicaltest.infrastructure.shared.enums.BankAccountType;
import lombok.Builder;

import java.math.BigDecimal;

/**
 * DTO that has the account information.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Builder
public record AccountDetailDTO(String accountNumber,
                               BankAccountType accountType,
                               BigDecimal balance,
                               Boolean status) {
}
