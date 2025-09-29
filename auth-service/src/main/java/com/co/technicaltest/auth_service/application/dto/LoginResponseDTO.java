package com.co.technicaltest.auth_service.application.dto;

import lombok.Builder;

/**
 * DTO that has the login information.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Builder
public record LoginResponseDTO(String token, String result) {
}
