package com.Andrew.service.booking.dto;

import com.Andrew.service.booking.entity.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TaskDto {

    private long id;
    private String description;
    private int hours;
    private LocalDateTime addedTime;
    private long projectId;
    private long userId;
}

