package com.co.technicaltest.application.dto;

import lombok.Builder;

/**
 * DTO that has the login information.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Builder
public record CustomerLoginResponseDTO(String username,
                                       String password,
                                       Boolean status) {
}
