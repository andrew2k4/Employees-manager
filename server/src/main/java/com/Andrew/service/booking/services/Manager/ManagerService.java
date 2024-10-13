package com.Andrew.service.booking.services.Manager;

import com.Andrew.service.booking.dto.ProjectDto;
import com.Andrew.service.booking.dto.TaskDto;

import java.util.List;

public interface ManagerService {


      boolean postProject(long userId, ProjectDto projectDto);
      List<ProjectDto> getAllProjects();
      ProjectDto getProjectById(long projectId);



      TaskDto getTaskById(long taskId);
      boolean postTask( TaskDto taskDto);
      List<TaskDto> getAllTasks();
}
