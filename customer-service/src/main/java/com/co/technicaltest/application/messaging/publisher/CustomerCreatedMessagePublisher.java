package com.co.technicaltest.application.messaging.publisher;

import com.co.technicaltest.domain.event.CustomerEvent;
import com.co.technicaltest.domain.port.output.messaging.publisher.CustomerMessagePublisher;
import com.co.technicaltest.domain.port.output.messaging.publisher.KafkaProducerPort;
import com.co.technicaltest.infrastructure.config.AccountServiceConfigData;
import com.co.technicaltest.infrastructure.shared.helper.KafkaMessageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Implementation service for publishing customer events.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Slf4j
@Component
public class CustomerCreatedMessagePublisher implements CustomerMessagePublisher {

    private final AccountServiceConfigData accountServiceConfigData;

    private final KafkaProducerPort<String, String> kafkaProducerPort;

    private final KafkaMessageHelper kafkaMessageHelper;

    public CustomerCreatedMessagePublisher(AccountServiceConfigData accountServiceConfigData,
                                           KafkaProducerPort<String, String> kafkaProducerPort,
                                           KafkaMessageHelper kafkaMessageHelper) {
        this.accountServiceConfigData = accountServiceConfigData;
        this.kafkaProducerPort = kafkaProducerPort;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void publish(CustomerEvent customerEvent) {

        log.info("Se recibe CustomerEvent para publicar creacion de usuario con id: {}",
                customerEvent.getCustomerId());

        try {

            String payload =
                    this.kafkaMessageHelper.createPayload(customerEvent);

            this.kafkaProducerPort.send(this.accountServiceConfigData.getCustomerTopicName(), customerEvent.getCustomerId().toString(), payload,
                    this.kafkaMessageHelper.getCallback(this.accountServiceConfigData.getCustomerTopicName(), payload));

            log.info("CustomerEvent enviado por kafka por creacion de cliente con id: {}",
                    customerEvent.getCustomerId());
        } catch (Exception e) {

            log.error("Error al enviar CustomerEvent por kafka al servicio de cuenta del usuario con id: {}," +
                    " error: {}", customerEvent.getCustomerId(), e.getMessage());
        }

    }
}
