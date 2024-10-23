package com.Andrew.service.booking.dto.projectdtos;


import com.Andrew.service.booking.dto.taskDtos.TaskDto;
import com.Andrew.service.booking.dto.userDtos.UserDto;
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


    private String description;

    private List<UserDto> users;

}

