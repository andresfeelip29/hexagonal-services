package com.co.technicaltest.application.mapper;

import com.co.technicaltest.domain.enums.EventType;
import com.co.technicaltest.domain.event.CustomerEvent;
import com.co.technicaltest.domain.model.Customer;
import org.springframework.stereotype.Component;

/**
 * Class to mapper domain model to event customer publish.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Component
public class CustomerEventMapper {

    public CustomerEvent toCreateCustomerEvent(Customer customer) {
        return CustomerEvent.builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .status(customer.getStatus())
                .username(customer.getUsername())
                .eventType(EventType.CUSTOMER_CREATED)
                .build();
    }

    public CustomerEvent toUpdateCustomerEvent(Customer customer) {
        return CustomerEvent.builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .status(customer.getStatus())
                .username(customer.getUsername())
                .eventType(EventType.CUSTOMER_UPDATED)
                .build();
    }

    public CustomerEvent toDeleteCustomerEvent(Customer customer) {
        return CustomerEvent.builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .status(customer.getStatus())
                .username(customer.getUsername())
                .eventType(EventType.CUSTOMER_DELETED)
                .build();
    }

}
