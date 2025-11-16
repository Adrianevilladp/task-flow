package com.adrian.taskflow.application.request;

import jakarta.validation.constraints.NotBlank;

public record ProjectCreateRequest(
        @NotBlank String name,
        @NotBlank String description

) {
}
