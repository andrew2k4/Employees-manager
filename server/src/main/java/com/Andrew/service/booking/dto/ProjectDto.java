package com.Andrew.service.booking.dto;


import lombok.Data;

import java.util.List;

@Data
public class ProjectDto {
    private long id;

    private String projectName;

    private String client;

    private String details;
    private String creatorName;

    private List<TaskDto> tasks;
    private List<UserDto> users;

}

