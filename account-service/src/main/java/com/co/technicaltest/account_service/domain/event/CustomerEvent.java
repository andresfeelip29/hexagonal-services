package com.co.technicaltest.account_service.domain.event;

import com.co.technicaltest.account_service.domain.enums.EventType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * Object domain for events customer.
 *
 * @author andres on 2025/06/15.
 * @version 1.0.0
 */
@Data
@Builder
public class CustomerEvent {
    private UUID customerId;
    private String username;
    private Boolean status;
    private String name;
    EventType eventType;
}
