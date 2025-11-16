package com.adrian.taskflow.application.service.impl;

import com.adrian.taskflow.application.service.RoleService;
import com.adrian.taskflow.domain.enums.ERole;
import com.adrian.taskflow.domain.model.Role;
import com.adrian.taskflow.domain.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final static ERole DEFAULT_ROLE = ERole.ROLE_USER;

    @Override
    public Role giveDefaultRole() {
        return Optional
                .of(roleRepository.findRoleByName(DEFAULT_ROLE))
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }
}
