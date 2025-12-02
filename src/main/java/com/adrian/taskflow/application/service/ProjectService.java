package com.adrian.taskflow.application.service;

import com.adrian.taskflow.application.request.ProjectCreateRequest;
import com.adrian.taskflow.application.request.ProjectUpdateRequest;
import com.adrian.taskflow.domain.model.Project;

import java.util.List;

public interface ProjectService {
    Project create(ProjectCreateRequest projectCreateRequest, Long userId);
    List<Project> findAll(Long userId);
    Project findById(Long id);
    Project update(Long idProject, ProjectUpdateRequest updateRequest, Long userId);
    void deleteById(Long projectId, Long userId);

}
