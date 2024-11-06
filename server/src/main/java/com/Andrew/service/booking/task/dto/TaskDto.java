package com.Andrew.service.booking.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private long id;
    private String description;
    private int hours;
    private LocalDateTime addedTime;
    private long projectId;
    private long userId;


    public TaskDto(long id, String description, int hours, LocalDateTime addedTime, long projectId) {
        this.id = id;
        this.description = description;
        this.hours = hours;
        this.addedTime = addedTime;
        this.projectId = projectId;
    }

}

