package com.Andrew.service.booking.dto.taskDtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto {

    private long id;
    private String description;
    private int hours;
    private LocalDateTime addedTime;
    private long projectId;
    private long userId;
}

