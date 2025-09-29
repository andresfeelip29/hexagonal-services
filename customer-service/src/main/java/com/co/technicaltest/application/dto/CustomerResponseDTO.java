package com.co.technicaltest.application.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

/**
 * DTO that has the customer response data.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Builder
public record CustomerResponseDTO(UUID customerId,
                                  String username,
                                  Boolean status,
                                  String name,
                                  Integer age,
                                  String gender,
                                  String identification,
                                  String address,
                                  String phone,
                                  List<AccountDetailDTO> accountDetail) {
}
