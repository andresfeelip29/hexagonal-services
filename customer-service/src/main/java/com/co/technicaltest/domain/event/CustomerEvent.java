package com.co.technicaltest.domain.event;

import com.co.technicaltest.domain.enums.EventType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * Object domain for events account.
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
