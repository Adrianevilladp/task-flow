package com.adrian.taskflow.infrastructure.validation.impl;

import com.adrian.taskflow.infrastructure.validation.NotNumbers;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Slf4j
public class NotNumbersImpl implements ConstraintValidator<NotNumbers, String> {
    public static final String LETTERS_PATTERN =  "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$";
    private static final Pattern pattern = Pattern.compile(LETTERS_PATTERN);

    @Override
    public void initialize(NotNumbers constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        try {
            return pattern.matcher(s).matches();
        } catch (PatternSyntaxException e) {
            log.error("An error occurred during password validation: {}", e.getMessage(), e);
            return false;
        }
    }
}
