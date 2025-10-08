package com.co.technicaltest.account_service.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * Object domain for customer.
 *
 * @author andres on 2025/06/15.
 * @version 1.0.0
 */
@Data
@Builder
public class Customer {
    private UUID customerId;
    private String username;
    private Boolean status;
    private String name;
}
