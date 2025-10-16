package com.co.technicaltest.transaction_service.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

/**
 * DTO that has the transaction validate reques information.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Builder
public record TransactionRequestDTO(@NotNull BigDecimal transactionValue,
                                    @NotBlank String accountOriginNumber,
                                    @NotBlank String accountDestinyNumber) {
}
