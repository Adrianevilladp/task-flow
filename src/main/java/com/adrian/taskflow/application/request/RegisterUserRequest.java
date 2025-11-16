package com.adrian.taskflow.application.request;

import com.adrian.taskflow.infrastructure.validation.NotNumbers;
import com.adrian.taskflow.infrastructure.validation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserRequest(
        @NotBlank @NotNumbers String firstName,
        @NotBlank @NotNumbers String lastName,
        @NotBlank @Email String email,
        @NotBlank @ValidPassword String password
) {
}
