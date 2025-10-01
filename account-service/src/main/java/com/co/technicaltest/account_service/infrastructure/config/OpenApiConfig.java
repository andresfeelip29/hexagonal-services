package com.co.technicaltest.account_service.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for open api.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microservice Calculation")
                        .version("1.0")
                        .description("Documentación de ejemplo con OpenAPI 3.0 y Spring Boot")
                );
    }
}
