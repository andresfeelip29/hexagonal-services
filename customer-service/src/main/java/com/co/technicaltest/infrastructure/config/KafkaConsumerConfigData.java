package com.co.technicaltest.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for data parameter consumer kafka.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-consumer-config")
public class KafkaConsumerConfigData {

    private String keyDeserializer;
    private String valueDeserializer;
    private String autoOffsetReset;
    private Integer sessionTimeoutMs;
    private Integer heartbeatIntervalMs;
    private Integer maxPollIntervalMs;
    private Integer maxPartitionFetchBytesDefault;
    private Integer maxPartitionFetchBytesBoostFactor;
    private Integer maxPollRecords;
    private Boolean batchListener;
    private Integer concurrencyLevel;
    private Boolean autoStartup;
    private Long pollTimeoutMs;

}