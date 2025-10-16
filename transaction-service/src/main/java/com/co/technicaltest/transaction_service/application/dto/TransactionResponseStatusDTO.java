package com.co.technicaltest.transaction_service.application.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;


import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO that has the transaction validate reques information.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Builder
public record TransactionResponseStatusDTO(UUID id,
                                           @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime createAt,
                                           String transactionStatus,
                                           String descriptionMovement) { }
