package com.adrian.taskflow.infrastructure.validation.impl;

import com.adrian.taskflow.infrastructure.validation.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Slf4j
public class ValidPasswordImpl implements ConstraintValidator<ValidPassword, String>{
    public static final String PASSWORD_PATTERN =
            "(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-.]).{8,30}";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return pattern.matcher(s).matches();
        } catch (PatternSyntaxException e) {
            log.error("An error occurred during password validation: {}", e.getMessage(), e);
            return false;
        }
    }
}
