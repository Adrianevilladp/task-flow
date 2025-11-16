package com.adrian.taskflow.application.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "email is mandatory")
        String email,

        @NotBlank(message = "Password is mandatory")
        String password) {
}
