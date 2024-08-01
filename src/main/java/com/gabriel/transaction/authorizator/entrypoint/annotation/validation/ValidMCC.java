package com.gabriel.transaction.authorizator.entrypoint.annotation.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidMCCValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMCC {
    String message() default "MCC inválido. Deve ser um número de 4 dígitos.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}