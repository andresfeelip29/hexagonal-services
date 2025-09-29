package com.co.technicaltest.auth_service.infrastructure.config;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;


/**
 * Configuration class for loead balancer feing client.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public class LoadBalancerConfig {

    @Bean
    public ServiceInstanceListSupplier serviceInstanceListSupplier(ConfigurableApplicationContext context) {
        return ServiceInstanceListSupplier.builder()
                .withBlockingDiscoveryClient()
                //.withSameInstancePreference()
                .build(context);
    }
}
