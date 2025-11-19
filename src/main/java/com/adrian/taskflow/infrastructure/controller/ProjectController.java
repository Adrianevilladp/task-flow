package com.adrian.taskflow.infrastructure.controller;

import com.adrian.taskflow.application.dto.ProjectDTO;
import com.adrian.taskflow.application.dto.TaskFlowMapper;
import com.adrian.taskflow.application.request.ProjectCreateRequest;
import com.adrian.taskflow.application.service.ProjectService;
import com.adrian.taskflow.domain.model.Project;
import com.adrian.taskflow.infrastructure.security.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/project")
@Slf4j
@Validated
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final TaskFlowMapper taskFlowMapper;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<ProjectDTO> create(@Valid @RequestBody ProjectCreateRequest projectCreateRequest,
                                             @AuthenticationPrincipal UserPrincipal user) {
        Project projectCreated = projectService.create(projectCreateRequest, user.getId());
        ProjectDTO projectResponse = taskFlowMapper.toDTO(projectCreated);

        return new ResponseEntity<>(projectResponse, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProjectDTO>> getAll() {
        return null;
    }

    @PutMapping("/")
    public ResponseEntity<ProjectDTO> update(@Valid @RequestBody ProjectDTO projectDTO) {
        return null;
    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete(@Valid @RequestBody ProjectDTO projectDTO) {
        return null;
    }
}
