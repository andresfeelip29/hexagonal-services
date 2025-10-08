package com.co.technicaltest.account_service.application.messaging.listener;

import com.co.technicaltest.account_service.application.exception.CustomerCreatedException;
import com.co.technicaltest.account_service.domain.model.Customer;
import com.co.technicaltest.account_service.domain.port.input.messaging.listener.CustomerMessageListener;
import com.co.technicaltest.account_service.domain.port.output.CustomerRepositoryPort;
import com.co.technicaltest.account_service.infrastructure.shared.enums.ExceptionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Implementation to customer listener event .
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Slf4j
@Service
public class CustomerCreatedMessageListener implements CustomerMessageListener {


    private final CustomerRepositoryPort customerRepositoryPort;


    public CustomerCreatedMessageListener(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void customerCreate(Customer customer) {

        log.info("Metodo: {}, para guarda cliente con id: {}",
                "[customerCreate]", customer.getCustomerId().toString());

        var result = this.customerRepositoryPort.saveCustomer(customer);


        if (result.isEmpty()) {

            log.error("No se puedo crear cliente con id: {}, ", customer.getCustomerId().toString());

            throw new CustomerCreatedException(
                    String.format(
                            ExceptionMessage.CUSTOMER_CREATED_EVENT_ERROR.getMessage()
                            , customer.getCustomerId().toString()));
        }

        log.info("Se creo cliente con id: {} en base de datos transmitido por servicio de cliente",
                customer.getCustomerId().toString());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void customerUpdate(Customer customer) {

        log.info("Metodo: {}, para actualizar cliente con id: {}",
                "[customerUpdate]", customer.getCustomerId().toString());

        var result = this.customerRepositoryPort.updateCustomer(customer);


        if (result.isEmpty()) {

            log.error("No se puedo actualizar cliente con id: {}, ",
                    customer.getCustomerId().toString());

            throw new CustomerCreatedException(
                    String.format(
                            ExceptionMessage.CUSTOMER_CREATED_EVENT_ERROR.getMessage()
                            , customer.getCustomerId().toString()));
        }

        log.info("Se actualizo cliente con id: {} en base de datos transmitido por servicio de cliente",
                customer.getCustomerId().toString());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void customerDelete(Customer customer) {

        log.info("Metodo: {}, para eliminar cliente con id: {}",
                "[customerDelete]", customer.getCustomerId().toString());


        this.customerRepositoryPort.deleteCustomer(customer.getCustomerId());
    }
}
