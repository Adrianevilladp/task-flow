package com.adrian.taskflow.application.service.impl;

import com.adrian.taskflow.application.dto.TaskFlowMapper;
import com.adrian.taskflow.application.exception.NotFoundException;
import com.adrian.taskflow.application.request.ProjectCreateRequest;
import com.adrian.taskflow.application.request.ProjectUpdateRequest;
import com.adrian.taskflow.application.service.ProjectService;
import com.adrian.taskflow.domain.enums.ERole;
import com.adrian.taskflow.domain.enums.ErrorCode;
import com.adrian.taskflow.domain.model.Project;
import com.adrian.taskflow.domain.model.User;
import com.adrian.taskflow.domain.repository.ProjectRepository;
import com.adrian.taskflow.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.security.access.AccessDeniedException;

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
    public List<Project> findAll(Long userId) {
        User ownerOrAdminOrMember = userRepository.getReferenceById(userId);
        boolean isAdmin = ownerOrAdminOrMember.getRoles().stream()
                .anyMatch(role -> role.getName().equals(ERole.ROLE_ADMIN));
        if (isAdmin) return projectRepository.findAll();

        return projectRepository.findAllByUser(userId);
    }

    @Override
    public Project findById(Long id) {
        return null;
    }

    @Override
    public Project update(Long idProject, ProjectUpdateRequest updateRequest, Long userId) {
        Project projectToUpdate = projectRepository.findById(idProject)
                .orElseThrow(() -> new NotFoundException(ErrorCode.PROJECT_NOT_FOUND.getMessage()));

        User ownerOrAdmin = userRepository.getReferenceById(userId);

        validatePermission(projectToUpdate, ownerOrAdmin);

        projectToUpdate.setName(
                updateRequest.name() == null ? projectToUpdate.getName() : updateRequest.name()
        );
        projectToUpdate.setDescription(
                updateRequest.description() == null ? projectToUpdate.getDescription() : updateRequest.description()
        );

        return projectRepository.saveAndFlush(projectToUpdate);
    }

    @Override
    public void deleteById(Long projectId, Long userId) {
        Project projectToDelete = projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.PROJECT_NOT_FOUND.getMessage()));

        User ownerOrAdmin = userRepository.getReferenceById(userId);

        validatePermission(projectToDelete, ownerOrAdmin);

        projectRepository.delete(projectToDelete);
    }

    private void validatePermission(Project project, User user) {
        boolean isAdmin = user.getRoles().stream()
                .anyMatch(role -> role.getName().equals(ERole.ROLE_ADMIN));
        boolean isOwner = project.getOwner().getId().equals(user.getId());

        if (!isAdmin && !isOwner) {
            throw new AccessDeniedException("You don't have permission to modify this project");
        }
    }
}
