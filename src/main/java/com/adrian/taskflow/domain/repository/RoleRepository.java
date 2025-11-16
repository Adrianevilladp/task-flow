package com.adrian.taskflow.domain.repository;

import com.adrian.taskflow.domain.enums.ERole;
import com.adrian.taskflow.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(ERole name);
}
