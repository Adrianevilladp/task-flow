package com.adrian.taskflow.application.service.impl;

import com.adrian.taskflow.application.dto.ProjectDTO;
import com.adrian.taskflow.application.dto.TaskFlowMapper;
import com.adrian.taskflow.application.request.ProjectCreateRequest;
import com.adrian.taskflow.application.service.ProjectService;
import com.adrian.taskflow.domain.model.Project;
import com.adrian.taskflow.domain.model.User;
import com.adrian.taskflow.domain.repository.ProjectRepository;
import com.adrian.taskflow.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TaskFlowMapper taskFlowMapper;

    @Override
    public Project create(ProjectCreateRequest projectCreateRequest, Long userId) {
        Project newProject = taskFlowMapper.fromRequestToEntity(projectCreateRequest);
        User owner = userRepository.getReferenceById(userId);

        newProject.setOwner(owner);

        return projectRepository.save(newProject);
    }

    @Override
    public List<Project> findAll() {
        return List.of();
    }

    @Override
    public Project findById(Long id) {
        return null;
    }

    @Override
    public Project update(ProjectDTO projectDTO) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
