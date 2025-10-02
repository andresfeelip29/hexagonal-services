package com.co.technicaltest.api_gateway.infrastructure.config;

import com.co.technicaltest.api_gateway.infrastructure.security.AuthenticationFilter;
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
    public RouteLocator routes(RouteLocatorBuilder builder, AuthenticationFilter authenticationFilter) {
        return builder
                .routes()
                .route(route ->
                        route.path("/api/v1/auth/**")
                                .uri("lb://auth-service")

                )
                .route(route ->
                        route.path("/api/v1/clientes/**")
                                .filters(f -> f.filter(authenticationFilter
                                        .apply(new AuthenticationFilter.Config())))
                                .uri("lb://customer-service")
                )
                .route(route ->
                        route.path("/api/v1/cuentas/**")
                                .filters(f -> f.filter(authenticationFilter
                                        .apply(new AuthenticationFilter.Config())))
                                .uri("lb://account-service")
                )
                .route(route ->
                        route.path("/api/v1/movimientos/**")
                                .filters(f -> f.filter(authenticationFilter
                                        .apply(new AuthenticationFilter.Config())))
                                .uri("lb://transaction-service")
                )
                .build();

    }
}
