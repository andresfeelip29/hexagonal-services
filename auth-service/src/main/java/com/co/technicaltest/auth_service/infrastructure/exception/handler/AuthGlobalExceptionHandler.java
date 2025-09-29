package com.co.technicaltest.auth_service.infrastructure.exception.handler;

import com.co.technicaltest.auth_service.infrastructure.adapter.dto.ErrorResponse;
import com.co.technicaltest.auth_service.application.exception.UserNotFoundInMicroservicesUserException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Global exception handler.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Slf4j
@RestControllerAdvice
public class AuthGlobalExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundInMicroservicesUserException.class, UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleException(Throwable e) {
        log.error(e.getMessage(), e);
        return this.buildErrorResponse(e, e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleException(BadCredentialsException e) {
        return this.buildErrorResponse(e, "Credenciales de acceso incorrectas!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {FeignException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleFeignStatusException(FeignException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", ex.status());
        try {
            ObjectMapper mapper = new ObjectMapper();
            ErrorResponse response = mapper.readValue(ex.contentUTF8(), ErrorResponse.class);
            error.put("message", response.message());
        } catch (Exception e) {
            error.put("message", "Error inesperado en comunicación con servicio remoto");
        }
        return ResponseEntity.status(ex.status()).body(error);
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return this.buildErrorResponse(exception, exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        List<String> errs = this.fieldValidate(e);
        return this.buildErrorResponse(e, String.join(", ", errs), HttpStatus.PRECONDITION_FAILED);
    }


    @ExceptionHandler(value = {ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleException(ValidationException validationException) {
        ResponseEntity<ErrorResponse> errorResponse;
        if (validationException instanceof ConstraintViolationException) {
            String violations = extractViolationsFromException((ConstraintViolationException) validationException);
            log.error(violations, validationException);
            errorResponse = this.buildErrorResponse(validationException,
                    validationException.getMessage(),
                    HttpStatus.BAD_REQUEST);
        } else {
            String exceptionMessage = validationException.getMessage();
            log.error(exceptionMessage, validationException);
            errorResponse = this.buildErrorResponse(validationException,
                    validationException.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        return errorResponse;
    }

    private String extractViolationsFromException(ConstraintViolationException validationException) {
        return validationException.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("--"));
    }


    private List<String> fieldValidate(MethodArgumentNotValidException result) {
        return result.getBindingResult()
                .getAllErrors()
                .stream()
                .map(err -> ((FieldError) err).getField().concat(" ").concat(Objects.requireNonNull(err.getDefaultMessage())))
                .toList();
    }

    protected ResponseEntity<ErrorResponse> buildErrorResponse(Throwable e, String message, HttpStatus httpStatus) {
        log.error(e.getMessage());
        return ResponseEntity.status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder()
                        .code(httpStatus.value())
                        .message(message)
                        .build());
    }

}
