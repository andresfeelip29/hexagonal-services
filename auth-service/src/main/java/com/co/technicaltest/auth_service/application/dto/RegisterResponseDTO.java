package com.co.technicaltest.auth_service.application.dto;


import java.util.UUID;

/**
 * DTO for response registered information.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public record RegisterResponseDTO(UUID customerId,
                                  String username,
                                  Boolean status,
                                  String name,
                                  Integer age,
                                  String gender,
                                  String identification,
                                  String address,
                                  String phone
                                ) {
}
