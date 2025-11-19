package com.adrian.taskflow.application.dto;

import com.adrian.taskflow.application.request.ProjectCreateRequest;
import com.adrian.taskflow.application.request.RegisterUserRequest;
import com.adrian.taskflow.domain.model.Invitation;
import com.adrian.taskflow.domain.model.Project;
import com.adrian.taskflow.domain.model.Task;
import com.adrian.taskflow.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskFlowMapper {
    Task toEntity(TaskDTO taskDTO);
    User toEntity(UserDTO userDTO);
    Project toEntity(ProjectDTO projectDTO);
    ProjectDTO toDTO(Project project);
    Project fromRequestToEntity(ProjectCreateRequest projectCreateRequest);
    User fromRequestToEntity(RegisterUserRequest registerUserRequest);
    Invitation toEntity(InvitationDTO invitationDTO);
}
