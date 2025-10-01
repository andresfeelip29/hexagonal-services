package com.co.technicaltest.account_service.domain.model;

import com.co.technicaltest.account_service.domain.enums.EventType;
import lombok.Data;

/**
 * Object domain for create send events account.
 *
 * @author andres on 2025/06/15.
 * @version 1.0.0
 */
@Data
public class AccountCreateEvent {
    Account account;
    EventType eventType;
}
