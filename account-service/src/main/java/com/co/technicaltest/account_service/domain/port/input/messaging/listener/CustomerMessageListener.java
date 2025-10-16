package com.co.technicaltest.account_service.domain.port.input.messaging.listener;

import com.co.technicaltest.account_service.domain.model.Customer;

/**
 * Interface to customer listener event .
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public interface CustomerMessageListener {

    /**
     * Method for creating a client based on the event captured by the listener customer.
     *
     * @param customer account domain data.
     */
    void customerCreate(Customer customer);

    /**
     * Method for updating a client based on the event captured by the listener customer.
     *
     * @param customer account domain data.
     */
    void customerUpdate(Customer customer);

    /**
     * Method for deleting a client based on the event captured by the listener customer.
     *
     * @param customer account domain data.
     */
    void customerDelete(Customer customer);


}
