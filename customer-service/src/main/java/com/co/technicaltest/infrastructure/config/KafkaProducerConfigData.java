package com.co.technicaltest.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for data parameter producer kafka.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-producer-config")
public class KafkaProducerConfigData {
    private String keySerializer;
    private String valueSerializer;
    private String acks;
    private Integer retries;
    private Integer batchSize;
    private Integer lingerMs;
    private Integer bufferMemory;
}