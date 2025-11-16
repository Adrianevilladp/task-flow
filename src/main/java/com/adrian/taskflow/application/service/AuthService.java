package com.adrian.taskflow.application.service;

import com.adrian.taskflow.application.request.LoginRequest;
import com.adrian.taskflow.application.request.RegisterUserRequest;
import com.adrian.taskflow.application.response.LoginResponse;
import com.adrian.taskflow.application.response.MessageResponse;

public interface AuthService {
    MessageResponse register(RegisterUserRequest registerUserRequest);
    LoginResponse login(LoginRequest loginRequest);
    MessageResponse logout(String token);
}
