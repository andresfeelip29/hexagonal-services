package com.co.technicaltest.domain.port.input.messaging.listener;

import com.co.technicaltest.domain.model.Account;

import java.util.UUID;

/**
 * Interface to account listener event .
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public interface AccountMessageListener {

    /**
     * Method for associate in database account listener to custemer.
     *
     * @param account account domain data.
     * @param customerId customer id.
     */
    void associateWithCustomer(Account account, UUID customerId);

    /**
     * Method for disassociate in database account listener to custemer.
     *
     * @param account account domain data.
     * @param customerId customer id.
     */
    void disassociateWithCustomer(Account account, UUID customerId);
}
