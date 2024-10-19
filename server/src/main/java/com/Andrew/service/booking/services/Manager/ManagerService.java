package com.Andrew.service.booking.services.Manager;

import com.Andrew.service.booking.dto.projectdtos.DashboardDto;
import com.Andrew.service.booking.dto.projectdtos.ProjectDto;
import com.Andrew.service.booking.dto.taskDtos.TaskDto;
import com.Andrew.service.booking.dto.userDtos.UserDashboardDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ManagerService {


      boolean postProject(long userId, DashboardDto projectDto);
      List<DashboardDto> getDashboard();
      DashboardDto getProjectById(long projectId);
      List<ProjectDto> getAllProject();


      TaskDto getTaskById(long taskId);
      boolean postTask(long userId, TaskDto taskDto);
      List<TaskDto> getAllTasks();


      boolean getUserDashboardDto();

}
