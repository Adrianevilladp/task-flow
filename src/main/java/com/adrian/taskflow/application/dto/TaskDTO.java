package com.adrian.taskflow.application.dto;

import com.adrian.taskflow.domain.enums.TaskPriority;
import com.adrian.taskflow.domain.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskDTO {

    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime dueDate;
    private ProjectDTO project;
    private UserDTO assignee;
}
