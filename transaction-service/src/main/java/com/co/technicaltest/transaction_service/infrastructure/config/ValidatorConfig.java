package com.co.technicaltest.transaction_service.infrastructure.config;

import jakarta.annotation.PreDestroy;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;

import java.util.Objects;

/**
 * Configuration class for validator.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Configuration
public class ValidatorConfig {


    private ValidatorFactory validatorFactory;


    @Bean
    public Validator getValidator() {
        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        return (Validator) this.validatorFactory.getValidator();
    }


    @PreDestroy
    public void destroy() {
        if (Objects.nonNull(this.validatorFactory)) {
            this.validatorFactory.close();
        }
    }
}
