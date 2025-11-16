package com.adrian.taskflow.infrastructure.validation;

import com.adrian.taskflow.infrastructure.validation.impl.NotNumbersImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NotNumbersImpl.class)
public @interface NotNumbers {
    String message() default
            """
                  Not numbers, use letters
                  """;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
