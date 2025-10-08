package com.co.technicaltest.infrastructure.adapter.output.messaging.producer;

import com.co.technicaltest.domain.exception.KafkaProducerException;
import com.co.technicaltest.domain.port.output.messaging.publisher.KafkaProducerPort;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

/**
 * Implemntation for kafka publisher .
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Slf4j
@Component
public class KafkaProducerAdapter<K extends Serializable, V> implements KafkaProducerPort<K, V> {

    private final KafkaTemplate<K, V> kafkaTemplate;

    public KafkaProducerAdapter(KafkaTemplate<K, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(String topicName, K key, V message, BiConsumer<SendResult<K, V>, Throwable> callback) {

        log.info("Enviado mensaje = {} a topico={}", message, topicName);

        try {

            CompletableFuture<SendResult<K, V>> kafkaResultFuture = kafkaTemplate.send(topicName, key, message);

            kafkaResultFuture.whenComplete(callback);

        } catch (KafkaException e) {

            log.error("Error en kafka al producir mensaje con key: {}, mensaje: {} y exception: {}", key, message,
                    e.getMessage());


            throw new KafkaProducerException("Error en kafka produciendo con key: " + key + " y mensaje: " + message);
        }

    }

    @PreDestroy
    public void close() {
        if (kafkaTemplate != null) {
            log.info("Se cierra productor de kafka!");
            kafkaTemplate.destroy();
        }
    }
}
