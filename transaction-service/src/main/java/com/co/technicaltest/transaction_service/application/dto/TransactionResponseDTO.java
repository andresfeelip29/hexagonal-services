package com.co.technicaltest.transaction_service.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO that has the response information from transaction.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Builder
public record TransactionResponseDTO(UUID id,
                                     @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime createAt,
                                     BigDecimal transactionValue,
                                     String accountOriginNumber,
                                     BigDecimal finalBalanceAccountOrigin,
                                     String accountDestinyNumber,
                                     BigDecimal finalBalanceAccountDestiny,
                                     String transactionStatus,
                                     String descriptionMovement,
                                     String customerName) {
}
