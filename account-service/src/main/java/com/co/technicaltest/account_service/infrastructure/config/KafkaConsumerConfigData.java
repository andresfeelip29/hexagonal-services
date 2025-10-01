package com.co.technicaltest.account_service.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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