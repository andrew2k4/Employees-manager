package com.Andrew.service.booking.dto;


import lombok.Data;

import java.util.List;

@Data
public class DashboardDto {
    private long id;

    private String projectName;
    private String clientName;

    private String details;

    private List<TaskDto> tasks;
    private List<UserDto> users;

}

