package com.adrian.taskflow.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvitationDTO {

    private String name;
    private String description;
    private ProjectDTO project;
    private UserDTO leader;
}
