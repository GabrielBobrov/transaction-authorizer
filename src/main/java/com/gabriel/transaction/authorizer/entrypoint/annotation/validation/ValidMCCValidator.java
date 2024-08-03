package com.gabriel.transaction.authorizer.entrypoint.annotation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidMCCValidator implements ConstraintValidator<ValidMCC, String> {
    @Override
    public void initialize(ValidMCC constraintAnnotation) {
    }

    @Override
    public boolean isValid(String mcc, ConstraintValidatorContext context) {
        return mcc != null && mcc.matches("\\d{4}");
    }
}