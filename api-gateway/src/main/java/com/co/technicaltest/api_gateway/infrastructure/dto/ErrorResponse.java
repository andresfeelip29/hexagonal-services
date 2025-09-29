package com.co.technicaltest.api_gateway.infrastructure.dto;

import lombok.Builder;

/**
 * Data transfer object for error response.
 *
 * @author Andres Polo on 2025/09/27.
 * @version 1.0.0
 */
@Builder
public record ErrorResponse(Integer code, String message) {
}
