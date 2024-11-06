package com.Andrew.service.booking.project.dto;


import com.Andrew.service.booking.user.dto.UserDto;
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

