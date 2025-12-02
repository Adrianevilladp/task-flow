package com.adrian.taskflow.application.request;

import jakarta.validation.constraints.NotBlank;

public record ProjectUpdateRequest (
        String name,
        String description
) {
}
