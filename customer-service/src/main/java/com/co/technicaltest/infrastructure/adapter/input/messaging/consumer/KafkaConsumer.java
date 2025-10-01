package com.co.technicaltest.infrastructure.adapter.input.messaging.consumer;


/**
 * Inteface for kafka lister .
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public interface KafkaConsumer<T> {


    /**
     * Method that receives information via Kafka for processing.
     *
     * @param messages payload from message.
     * @param key unique key from kafka event publisher.
     * @param partition partition os pubblisher even.
     * @param offset position of message that event publisher.
     */
    void receive(T messages, String key, Integer partition, Long offset);
}
