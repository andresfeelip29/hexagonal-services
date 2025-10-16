package com.co.technicaltest.transaction_service.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for kafka topic.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "account-service")
public class AccountServiceConfigData {
    private String customerTopicName;
    private String customerSpringId;
    private String customerGroupId;
}
