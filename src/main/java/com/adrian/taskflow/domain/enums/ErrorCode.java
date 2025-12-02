package com.adrian.taskflow.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    PROJECT_NOT_FOUND("Project not found"),
    USER_NOT_ALLOWED("User not allowed");

    private final String message;

}
