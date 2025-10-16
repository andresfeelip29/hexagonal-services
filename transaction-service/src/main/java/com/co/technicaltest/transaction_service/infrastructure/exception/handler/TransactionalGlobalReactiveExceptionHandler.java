package com.co.technicaltest.transaction_service.infrastructure.exception.handler;

import com.co.technicaltest.transaction_service.domain.exception.TransactionException;
import com.co.technicaltest.transaction_service.infrastructure.exception.TransactionStatusExepcion;
import com.co.technicaltest.transaction_service.infrastructure.shared.dto.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;

/**
 * Global exception handler.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Slf4j
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TransactionalGlobalReactiveExceptionHandler implements ErrorWebExceptionHandler {


    private final ObjectMapper objectMapper;

    public TransactionalGlobalReactiveExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {

        log.error("Error global en el procesamiento de la peticion", ex);

        return switch (ex) {
            case TransactionException transactionException ->
                    this.buildErrorResponse(exchange, ex.getMessage(), HttpStatus.NOT_FOUND);
            case AccountNotFoundException accountNotFoundException ->
                    this.buildErrorResponse(exchange, ex.getMessage(), HttpStatus.NOT_FOUND);
            case WebClientResponseException webClientResponseException ->
                    this.buildErrorResponse(exchange, ex.getMessage(), HttpStatus.NOT_FOUND);
            case ConstraintViolationException constraintViolationException ->
                    this.buildErrorResponse(exchange, ex.getMessage(), HttpStatus.BAD_REQUEST);
            case TransactionStatusExepcion transactionStatusExepcion ->
                    this.buildErrorResponse(exchange, ex.getMessage(), HttpStatus.BAD_REQUEST);
            case ValidationException validationException  ->
                    this.buildErrorResponse(exchange, ex.getMessage(), HttpStatus.PRECONDITION_FAILED);
            default -> this.buildErrorResponse(exchange, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        };

    }

    private Mono<Void> buildErrorResponse(ServerWebExchange exchange, String message, HttpStatus httpStatus) {

        ErrorResponse response = ErrorResponse.builder()
                .code(httpStatus.value())
                .message(message)
                .timestamp(LocalDateTime.now().toString())
                .build();

        try {
            final var bytes = this.objectMapper.writeValueAsBytes(response);
            final var buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            exchange.getResponse().setStatusCode(httpStatus);
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return exchange.getResponse().writeWith(Mono.just(buffer));

        } catch (JsonProcessingException e) {
            log.error("Error en el procesamiento de la peticion", e);
            return Mono.error(e);
        }

    }
}
