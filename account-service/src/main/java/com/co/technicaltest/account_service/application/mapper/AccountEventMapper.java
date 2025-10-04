package com.co.technicaltest.account_service.application.mapper;

import com.co.technicaltest.account_service.domain.enums.EventType;
import com.co.technicaltest.account_service.domain.event.AccountEvent;
import com.co.technicaltest.account_service.domain.model.Account;
import org.springframework.stereotype.Component;

/**
 * Class to mapper DTO from domain objetc.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Component
public class AccountEventMapper {


    public AccountEvent toCreateAccountEvent(Account account) {
        return AccountEvent.builder()
                .account(account)
                .eventType(EventType.ACCOUNT_CREATED)
                .build();
    }
}
