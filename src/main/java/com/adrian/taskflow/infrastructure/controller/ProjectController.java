package com.adrian.taskflow.infrastructure.controller;

import com.adrian.taskflow.application.dto.ProjectDTO;
import com.adrian.taskflow.application.dto.TaskFlowMapper;
import com.adrian.taskflow.application.request.ProjectCreateRequest;
import com.adrian.taskflow.application.request.ProjectUpdateRequest;
import com.adrian.taskflow.application.service.ProjectService;
import com.adrian.taskflow.domain.model.Project;
import com.adrian.taskflow.infrastructure.security.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<ProjectDTO>> getAll(@AuthenticationPrincipal UserPrincipal user) {
        List<Project> projects = projectService.findAll(user.getId());
        List<ProjectDTO> responseProjects = projects.stream().map(taskFlowMapper::toDTO).toList();

        return new ResponseEntity<>(responseProjects, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ProjectDTO> update(@PathVariable(name = "id") String id,
                                             @Valid @RequestBody ProjectUpdateRequest projectUpdateRequest,
                                             @AuthenticationPrincipal UserPrincipal user) {
        Project projectToUpdate = projectService.update(Long.parseLong(id), projectUpdateRequest, user.getId());
        ProjectDTO projectResponse = taskFlowMapper.toDTO(projectToUpdate);

        return new ResponseEntity<>(projectResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable(name = "id") String id,
                                    @AuthenticationPrincipal UserPrincipal user) {
        projectService.deleteById(Long.parseLong(id), user.getId());

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
