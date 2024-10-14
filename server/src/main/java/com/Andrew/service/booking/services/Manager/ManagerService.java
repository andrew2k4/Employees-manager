package com.Andrew.service.booking.services.Manager;

import com.Andrew.service.booking.dto.DashboardDto;
import com.Andrew.service.booking.dto.ProjectDto;
import com.Andrew.service.booking.dto.TaskDto;

import java.util.List;

public interface ManagerService {


      boolean postProject(long userId, DashboardDto projectDto);
      List<DashboardDto> getDashboard();
      DashboardDto getProjectById(long projectId);
      List<ProjectDto> getAllProject();


      TaskDto getTaskById(long taskId);
      boolean postTask(long userId, TaskDto taskDto);
      List<TaskDto> getAllTasks();
}
