package com.co.technicaltest.account_service.application.dto;

import com.co.technicaltest.account_service.domain.enums.BankAccountType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

/**
 * DTO that has the account validate reques information.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Builder
public record AccountRequestDTO(@NotNull BankAccountType accountType, @NotNull @Positive BigDecimal balance,
                                @NotNull Boolean status, @NotNull @Pattern(
        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$",
        message = "Debe ser un UUID válido"
) String customerId) { }
