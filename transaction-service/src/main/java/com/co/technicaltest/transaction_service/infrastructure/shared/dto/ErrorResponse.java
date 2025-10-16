package com.co.technicaltest.transaction_service.infrastructure.shared.dto;

import lombok.Builder;

/**
 * DTO that has the error information.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Builder
public record ErrorResponse(Integer code, String message, String timestamp) {
}