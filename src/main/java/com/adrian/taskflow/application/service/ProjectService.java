package com.adrian.taskflow.application.service;

import com.adrian.taskflow.application.dto.ProjectDTO;
import com.adrian.taskflow.application.request.ProjectCreateRequest;
import com.adrian.taskflow.domain.model.Project;

import java.util.List;

public interface ProjectService {
    Project create(ProjectCreateRequest projectCreateRequest, Long userId);
    List<Project> findAll();
    Project findById(Long id);
    Project update(ProjectDTO projectDTO);
    void deleteById(Long id);

}
