package com.co.technicaltest.transaction_service.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for transaction data kafka topic.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "transaction-service")
public class TransactionServiceConfigData {
    private String transactionTopicName;
    private String transactionSpringId;
    private String transactionGroupId;
}
