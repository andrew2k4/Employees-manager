package com.Andrew.service.booking.dto;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DashboardDto {
    private long id;

    private String projectName;
    private String clientName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    private String details;

    private List<TaskDto> tasks;
    private List<UserDto> users;

}

