package com.co.technicaltest.transaction_service.infrastructure.adapter.input.rest;

import com.co.technicaltest.transaction_service.application.dto.TransactionRequestDTO;
import com.co.technicaltest.transaction_service.application.dto.TransactionResponseDTO;
import com.co.technicaltest.transaction_service.domain.port.input.TransactionUseCase;
import com.co.technicaltest.transaction_service.infrastructure.shared.utils.ParseDate;
import com.co.technicaltest.transaction_service.infrastructure.shared.validators.ReactiveValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Account management handler.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Slf4j
@Component
public class TransactionHandler {


    private final TransactionUseCase transactionUseCase;

    private final ReactiveValidator reactiveValidator;


    public TransactionHandler(TransactionUseCase transactionUseCase,
                              ReactiveValidator reactiveValidator) {
        this.transactionUseCase = transactionUseCase;
        this.reactiveValidator = reactiveValidator;
    }

    public Mono<ServerResponse> findAllTransaction(ServerRequest request) {

        return this.transactionUseCase.findAllTransactions()
                .doOnSubscribe(sub -> log.info("Iniciando consulta de transacciones"))
                .doOnNext(tx -> log.debug("Transacción encontrada: {}", tx))
                .collectList()
                .flatMap(transactions -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(transactions)
                        .doOnSuccess(resp -> log.info("Consulta de transacciones completada con éxito")))
                .onErrorResume(error -> {
                    log.error("Error consultando transacciones: {}", error.getMessage(), error);
                    return ServerResponse
                            .badRequest()
                            .build();
                });
    }


    public Mono<ServerResponse> findTransactionById(ServerRequest request) {

        UUID id = UUID.fromString(request.pathVariable("id"));

        return this.transactionUseCase.findTransactionById(id)
                .doOnSubscribe(sub -> log.info("Iniciando consulta de transacciones por id: {}", id.toString()))
                .doOnNext(tx -> log.debug("✅ Transacción encontrada: {}", tx))
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(result))
                ).switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .build());
    }


    public Mono<ServerResponse> saveTransaction(ServerRequest request) {

        return request.bodyToMono(TransactionRequestDTO.class)
                .flatMap(this.reactiveValidator::validate)
                .flatMap(transactionDto ->
                        this.transactionUseCase.saveTransaction(transactionDto)
                                .flatMap(result -> ServerResponse
                                        .created(URI.create("/api/v1/movimientos/" + result.id().toString()))
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromValue(result))
                                ).switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .build()))
                .doOnSuccess(result -> log.info("✅ Transaccion guardada correctamente: {}", result))
                .doOnCancel(() -> log.warn("⚠️ Transacción cancelada por el cliente"));
    }


    public Mono<ServerResponse> filterTransactionForRageDateAndClientId(ServerRequest request) {

        return Mono.justOrEmpty(request.queryParam("initDate"))
                .zipWith(Mono.justOrEmpty(request.queryParam("endDate")))
                .flatMap(tuple -> {
                    String initDateStr = tuple.getT1();
                    String endDateStr = tuple.getT2();

                    LocalDateTime dateFrom;
                    LocalDateTime dateTo;

                    try {

                        dateFrom = ParseDate.formattDateTimeToParam(initDateStr);
                        dateTo = ParseDate.formattDateTimeToParam(endDateStr);

                    } catch (Exception e) {
                        log.warn("❌ Error parseando fechas: {} - {}", initDateStr, endDateStr, e);
                        return ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(Map.of("error", "Formato de fecha inválido"));
                    }

                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(transactionUseCase.findAllTransactionsByDateRange(dateFrom, dateTo),
                                    TransactionResponseDTO.class);
                })
                .switchIfEmpty(ServerResponse.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(Map.of("error", "Parámetros 'initDate' y 'endDate' son requeridos")))
                .doOnSuccess(resp -> log.info("✅ Filtro de transacciones por rango de fecha aplicado"))
                .doOnCancel(() -> log.warn("⚠️ Petición cancelada por el cliente"));
    }


}
