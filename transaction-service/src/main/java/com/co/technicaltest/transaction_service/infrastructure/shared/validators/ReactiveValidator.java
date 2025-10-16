package com.co.technicaltest.transaction_service.infrastructure.shared.validators;

import jakarta.validation.ValidationException;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class to reactive validation to arguments con request.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Component
@RequiredArgsConstructor
public class ReactiveValidator {

    private Validator validator;

    public <T> Mono<T> validate(T obj) {

        Errors errors = new BeanPropertyBindingResult(obj, obj.getClass().getName());
        this.validator.validate(obj, errors);

        if (!errors.hasErrors()) {
            return Mono.just(obj);
        }
        final var result = errors.getFieldErrors()
                .stream()
                .map(fieldError -> "Campo ".concat(fieldError.getField())
                        .concat(" ")
                        .concat(Objects.requireNonNull(fieldError.getDefaultMessage())))
                .collect(Collectors.joining(","));
        return Mono.error(new ValidationException(result));
    }
}
