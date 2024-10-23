package com.Andrew.service.booking.services.Manager;

import com.Andrew.service.booking.dto.projectdtos.DashboardDto;
import com.Andrew.service.booking.dto.projectdtos.ProjectDetailsDto;
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
      boolean updateProjectById(long projectId, ProjectDetailsDto projectDetailsDto);
      public boolean deleteProjectById(long projectId);

      TaskDto getTaskById(long taskId);
      boolean postTask(long userId, TaskDto taskDto);
      List<TaskDto> getAllTasks();
      boolean updateTask(long taskId, TaskDto taskDto);
      public boolean deleteTaskById(long taskId);


      List<UserDashboardDto> getUserDashboardDto();
      List<UserDashboardDto> getUserDashboardFilteredDto(String startDate, String endDate);
      UserDashboardDto getUserById(long id);
}
