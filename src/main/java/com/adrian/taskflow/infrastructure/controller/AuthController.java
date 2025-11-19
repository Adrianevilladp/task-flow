package com.adrian.taskflow.infrastructure.controller;

import com.adrian.taskflow.application.request.LoginRequest;
import com.adrian.taskflow.application.request.RegisterUserRequest;
import com.adrian.taskflow.application.response.LoginResponse;
import com.adrian.taskflow.application.response.MessageResponse;
import com.adrian.taskflow.application.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
@Validated
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        MessageResponse response = authService.register(registerUserRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
