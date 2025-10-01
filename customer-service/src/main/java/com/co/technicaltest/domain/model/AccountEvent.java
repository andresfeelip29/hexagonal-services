package com.co.technicaltest.domain.model;

import com.co.technicaltest.domain.enums.EventType;
import lombok.Data;

/**
 * Object domain for events account.
 *
 * @author andres on 2025/06/15.
 * @version 1.0.0
 */
@Data
public class AccountEvent {
    Account account;
    EventType eventType;
}
