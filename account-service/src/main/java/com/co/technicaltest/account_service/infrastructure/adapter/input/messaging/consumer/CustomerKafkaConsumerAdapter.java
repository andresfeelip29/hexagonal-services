package com.co.technicaltest.account_service.infrastructure.adapter.input.messaging.consumer;

import com.co.technicaltest.account_service.domain.enums.EventType;
import com.co.technicaltest.account_service.domain.event.CustomerEvent;
import com.co.technicaltest.account_service.domain.model.Customer;
import com.co.technicaltest.account_service.domain.port.input.messaging.listener.CustomerMessageListener;
import com.co.technicaltest.account_service.domain.port.input.messaging.listener.KafkaConsumerPort;
import com.co.technicaltest.account_service.infrastructure.mapper.CustomerEventMessagingMapper;
import com.co.technicaltest.account_service.infrastructure.shared.helper.KafkaMessageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Implementation to kafka listener .
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Slf4j
@Component
public class CustomerKafkaConsumerAdapter implements KafkaConsumerPort<String> {


    private final CustomerEventMessagingMapper mapper;

    private final KafkaMessageHelper kafkaMessageHelper;

    private final Map<EventType, Consumer<Customer>> eventHandlers;


    public CustomerKafkaConsumerAdapter(CustomerMessageListener customerMessageListener,
                                        CustomerEventMessagingMapper mapper,
                                        KafkaMessageHelper kafkaMessageHelper) {
        this.mapper = mapper;
        this.kafkaMessageHelper = kafkaMessageHelper;
        this.eventHandlers = Map.of(
                EventType.CUSTOMER_CREATED, customerMessageListener::customerCreate,
                EventType.CUSTOMER_UPDATED, customerMessageListener::customerUpdate,
                EventType.CUSTOMER_DELETED, customerMessageListener::customerDelete
        );
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @KafkaListener(id = "${account-service.customer-spring-id}",
            topics = "${account-service.customer-topic-name}",
            groupId = "${account-service.customer-group-id}")
    public void receive(@Payload String messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) String key,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                        @Header(KafkaHeaders.OFFSET) Long offset) {

        log.info("Se recibe mensaje: {} para creacion de cliente con id: {} , particion {} y offsets {}",
                messages,
                key,
                partition,
                offset);

        CustomerEvent customerEvent =
                this.kafkaMessageHelper.getEventPayload(messages, CustomerEvent.class);

        if (!Objects.isNull(customerEvent)) {
            Customer customer =
                    this.mapper.customerfromCustomerEvent(customerEvent);
            this.eventHandlers
                    .getOrDefault(customerEvent.getEventType(),
                            c -> log.warn("No es un evento valido: {}", customerEvent.getEventType()))
                    .accept(customer);
        }

    }
}
