package com.co.technicaltest.account_service.domain.port.output.messaging.publisher;

import com.co.technicaltest.account_service.domain.event.AccountEvent;

import java.util.UUID;

/**
 * interface for publishing account events.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public interface AccountMessagePublisher {

    /**
     * Method publish account event
     *
     * @param accountPublisherEvent data event for account publication.
     */
    void publish(AccountEvent accountPublisherEvent, UUID customerId);

}
