package com.adrian.taskflow.application.service.impl;

import com.adrian.taskflow.application.dto.TaskFlowMapper;
import com.adrian.taskflow.application.request.LoginRequest;
import com.adrian.taskflow.application.request.RegisterUserRequest;
import com.adrian.taskflow.application.response.LoginResponse;
import com.adrian.taskflow.application.response.MessageResponse;
import com.adrian.taskflow.application.service.AuthService;
import com.adrian.taskflow.application.service.RoleService;
import com.adrian.taskflow.domain.model.Role;
import com.adrian.taskflow.domain.model.User;
import com.adrian.taskflow.domain.repository.UserRepository;
import com.adrian.taskflow.infrastructure.security.JwtTokenProvider;
import com.adrian.taskflow.infrastructure.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;
    private final TaskFlowMapper taskFlowMapper;

    @Override
    public MessageResponse register(RegisterUserRequest registerUserRequest) {
        User newUser = taskFlowMapper.fromRequestToEntity(registerUserRequest);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        Role defaultRole = roleService.giveDefaultRole();
        defaultRole.addUser(newUser);
        newUser.getRoles().add(defaultRole);

        User savedUser = userRepository.save(newUser);

        return new MessageResponse(String.format("user registered %s", savedUser.getEmail()));
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));
        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken((UserPrincipal) authentication.getPrincipal());

        return new LoginResponse(jwt);
    }

    @Override
    public MessageResponse logout(String token) {
        return null;
    }
}
