package com.co.technicaltest.account_service.infrastructure.mapper;

import com.co.technicaltest.account_service.domain.event.CustomerEvent;
import com.co.technicaltest.account_service.domain.model.Customer;
import org.springframework.stereotype.Component;

/**
 * Class to mapper event information ato domain object.
 *
 * @author andres on 2025/09/28.
 * @version 1.0.0
 */
@Component
public class CustomerEventMessagingMapper {


    public Customer customerfromCustomerEvent(CustomerEvent customerEvent) {
        return Customer.builder()
                .customerId(customerEvent.getCustomerId())
                .name(customerEvent.getName())
                .status(customerEvent.getStatus())
                .username(customerEvent.getUsername())
                .build();
    }

}
