package com.co.technicaltest.infrastructure.adapter.input.messaging.consumer;


import com.co.technicaltest.domain.enums.EventType;
import com.co.technicaltest.domain.event.AccountEvent;
import com.co.technicaltest.domain.model.Account;
import com.co.technicaltest.domain.port.input.messaging.listener.AccountMessageListener;
import com.co.technicaltest.domain.port.input.messaging.listener.KafkaConsumerPort;
import com.co.technicaltest.infrastructure.shared.helper.KafkaMessageHelper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Implementation to kafka listener .
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Slf4j
@Component
public class AccountKafkaConsumerAdapter implements KafkaConsumerPort<String> {


    private final KafkaMessageHelper kafkaMessageHelper;

    private final Map<EventType, BiConsumer<Account, UUID>> eventHandlers;


    public AccountKafkaConsumerAdapter(AccountMessageListener accountMessageListener,
                                       KafkaMessageHelper kafkaMessageHelper) {
        this.kafkaMessageHelper = kafkaMessageHelper;
        this.eventHandlers = Map.of(
                EventType.ACCOUNT_CREATED, accountMessageListener::associateWithCustomer,
                EventType.ACCOUNT_UPDATED, accountMessageListener::associateWithCustomer,
                EventType.ACCOUNT_DELETED, accountMessageListener::disassociateWithCustomer
        );
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @KafkaListener(id = "${customer-service.customer-spring-id}",
            topics = "${customer-service.customer-topic-name}",
            groupId = "${customer-service.customer-group-id}")
    public void receive(@Payload String messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) String key,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                        @Header(KafkaHeaders.OFFSET) Long offset) {

        log.info("Se recibe mensaje: {} para creacion de cuentas asociada al usuario id: {} , particion {} y offsets {}",
                messages,
                key,
                partition,
                offset);

        UUID customerId = this.kafkaMessageHelper.uuidFromString(key);

        AccountEvent accountEvent = this.kafkaMessageHelper
                .getEventPayload(messages, AccountEvent.class);

        if (!Objects.isNull(accountEvent) && !Objects.isNull(customerId)) {
            this.eventHandlers
                    .getOrDefault(accountEvent.getEventType(),
                            (acc, id) -> log.warn("No es un evento valido: {}", accountEvent.getEventType()))
                    .accept(accountEvent.getAccount(), customerId);

        }

    }
}
