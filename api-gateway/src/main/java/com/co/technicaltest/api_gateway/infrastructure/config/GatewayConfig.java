package com.co.technicaltest.api_gateway.infrastructure.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Route configurations.
 *
 * @author Andres Polo on 2025/09/27.
 * @version 1.0.0
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(route ->
                        route.path("/api/v1/auth/**")
                        .uri("lb://auth-service")
                )
                .route(route ->
                        route.path("/api/v1/clientes/**")
                                .uri("lb://customer-service")
                )
                .build();

    }
}
