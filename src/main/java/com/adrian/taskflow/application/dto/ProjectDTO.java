package com.adrian.taskflow.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProjectDTO {
    private String name;
    private String description;
    private UserDTO owner;
    private Set<TaskDTO> tasks;
}
