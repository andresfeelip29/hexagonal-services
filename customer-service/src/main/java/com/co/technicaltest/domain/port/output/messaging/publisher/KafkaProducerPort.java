package com.co.technicaltest.domain.port.output.messaging.publisher;

import org.springframework.kafka.support.SendResult;

import java.io.Serializable;
import java.util.function.BiConsumer;

/**
 * Inteface for kafka publisher .
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */

public interface KafkaProducerPort<K extends Serializable, V> {


    /**
     * Method that receives information via Kafka for processing.
     *
     * @param topicName topic name.
     * @param key       unique key from kafka event publisher.
     * @param message   payload from message.
     * @param callback  callback to verify status of sent message.
     */
    void send(String topicName, K key, V message, BiConsumer<SendResult<K, V>, Throwable> callback);
}
