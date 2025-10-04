package com.co.technicaltest.account_service.application.messaging.publisher;

import com.co.technicaltest.account_service.domain.event.AccountEvent;
import com.co.technicaltest.account_service.domain.port.output.messaging.publisher.AccountMessagePublisher;
import com.co.technicaltest.account_service.domain.port.output.messaging.publisher.KafkaProducerPort;
import com.co.technicaltest.account_service.infrastructure.config.CustomerServiceConfigData;
import com.co.technicaltest.account_service.infrastructure.shared.helper.KafkaMessageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;


/**
 * Implementation service for publishing account events.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Slf4j
@Component
public class AccountCreatedMessagePublisher implements AccountMessagePublisher {


    private final CustomerServiceConfigData customerServiceConfigData;

    private final KafkaProducerPort<String, String> kafkaProducerPort;

    private final KafkaMessageHelper kafkaMessageHelper;


    public AccountCreatedMessagePublisher(CustomerServiceConfigData customerServiceConfigData,
                                          KafkaProducerPort<String, String> kafkaProducerPort,
                                          KafkaMessageHelper kafkaMessageHelper) {
        this.customerServiceConfigData = customerServiceConfigData;
        this.kafkaProducerPort = kafkaProducerPort;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void publish(AccountEvent accountPublisherEvent, UUID customerId) {

        log.info("Se recibe AccountEvent para cliente con id: {}",
                customerId);

        try {

            String payload =
                    this.kafkaMessageHelper.createPayload(accountPublisherEvent);

            this.kafkaProducerPort.send(this.customerServiceConfigData.getCustomerTopicName(), customerId.toString(), payload,
                    this.kafkaMessageHelper.getCallback(this.customerServiceConfigData.getCustomerTopicName(), payload));

            log.info("AccountEvent enviado por kafka para cliente con id: {}",
                    customerId);
        } catch (Exception e) {

            log.error("Error al enviar CustomerCreatedEvent por kafka al cliinete con id: {}," +
                    " error: {}", customerId, e.getMessage());
        }

    }

}
