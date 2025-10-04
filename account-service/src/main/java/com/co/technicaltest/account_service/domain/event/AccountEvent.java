package com.co.technicaltest.account_service.domain.event;

import com.co.technicaltest.account_service.domain.enums.EventType;
import com.co.technicaltest.account_service.domain.model.Account;
import lombok.Builder;
import lombok.Data;

/**
 * Object domain for events account.
 *
 * @author andres on 2025/06/15.
 * @version 1.0.0
 */
@Data
@Builder
public class AccountEvent {
    Account account;
    EventType eventType;
}
