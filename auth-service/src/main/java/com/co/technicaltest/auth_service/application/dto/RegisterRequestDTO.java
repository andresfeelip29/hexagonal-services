package com.co.technicaltest.auth_service.application.dto;

import com.co.technicaltest.auth_service.infrastructure.shared.validation.anotations.ValidUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

/**
 * DTO that has the register customer information.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Builder
public record RegisterRequestDTO(@ValidUsername @NotNull @NotBlank String username,
                                 @NotNull @NotBlank String password,
                                 @NotNull Boolean status,
                                 @NotNull @NotBlank String name,
                                 @NotNull Integer age,
                                 @NotNull @NotBlank String gender,
                                 @NotNull @NotBlank String identification,
                                 @NotNull @NotBlank String address,
                                 @NotNull @NotBlank String phone) {
}
