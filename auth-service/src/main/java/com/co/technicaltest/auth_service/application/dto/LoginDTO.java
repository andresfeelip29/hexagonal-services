package com.co.technicaltest.auth_service.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for login credential.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public record LoginDTO(@NotNull @NotBlank String username,
                       @NotNull @NotBlank String password) {
}
