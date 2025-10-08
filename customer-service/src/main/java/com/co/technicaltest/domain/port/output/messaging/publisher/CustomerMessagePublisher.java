package com.co.technicaltest.domain.port.output.messaging.publisher;

import com.co.technicaltest.domain.event.CustomerEvent;

import java.util.UUID;

/**
 * interface for publishing customer events.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public interface CustomerMessagePublisher {

    /**
     * Method publish customer event
     *
     * @param customerEvent data event for customer publication.
     */
    void publish(CustomerEvent customerEvent);
}
