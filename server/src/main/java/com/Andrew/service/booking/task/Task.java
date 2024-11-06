package com.Andrew.service.booking.task;

import com.Andrew.service.booking.task.dto.TaskDto;
import com.Andrew.service.booking.project.Project;
import com.Andrew.service.booking.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;
    private int hours;
    private LocalDateTime addedTime; // Changed from Date to LocalDateTime

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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
        taskDto.setUserId(user.getId());

        return taskDto;
    }
}
