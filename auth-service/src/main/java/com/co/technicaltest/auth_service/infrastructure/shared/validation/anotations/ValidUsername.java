package com.co.technicaltest.auth_service.infrastructure.shared.validation.anotations;

import com.co.technicaltest.auth_service.infrastructure.shared.validation.validator.ValidUsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Anotations for validate username.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Documented
@Constraint(validatedBy = ValidUsernameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface ValidUsername {

    String message() default "{custom.validation.constrainst.ValidUsername.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}