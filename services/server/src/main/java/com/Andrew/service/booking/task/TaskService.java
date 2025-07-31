package com.Andrew.service.booking.task;

import com.Andrew.service.booking.task.dto.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto getTaskById(long taskId);
    boolean postTask(long userId, TaskDto taskDto);
    List<TaskDto> getAllTasks();
    boolean updateTask(long taskId, TaskDto taskDto);
    public boolean deleteTaskById(long taskId);
}
