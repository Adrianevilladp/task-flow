package com.adrian.taskflow.infrastructure.controller;

import com.adrian.taskflow.application.dto.ProjectDTO;
import com.adrian.taskflow.domain.model.Project;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
public class ProjectController {

    @PostMapping("/")
    public ResponseEntity<ProjectDTO> create() {
        return null;
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
