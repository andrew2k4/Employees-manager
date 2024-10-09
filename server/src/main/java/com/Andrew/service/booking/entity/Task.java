package com.Andrew.service.booking.entity;

import com.Andrew.service.booking.dto.TaskDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;
    private int hours;
    private LocalDateTime addedTime; // Changed from Date to LocalDateTime

    private long user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public TaskDto getDto() {
        TaskDto taskDto = new TaskDto();

        taskDto.setId(id);
        taskDto.setDescription(description);
        taskDto.setHours(hours);
        taskDto.setAddedTime(addedTime);
        taskDto.setProjectId(project.getId());

            taskDto.setUserId(user); // Assuming TaskDto has a userId field

        return taskDto;
    }
}
