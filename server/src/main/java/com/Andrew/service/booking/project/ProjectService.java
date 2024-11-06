package com.Andrew.service.booking.project;

import com.Andrew.service.booking.project.dto.DashboardDto;
import com.Andrew.service.booking.project.dto.ProjectDetailsDto;
import com.Andrew.service.booking.project.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    boolean postProject(long userId, DashboardDto projectDto);
    List<DashboardDto> getProjectDashboard();
    DashboardDto getProjectById(long projectId);
    List<ProjectDto> getAllProject();
    boolean updateProjectById(long projectId, ProjectDetailsDto projectDetailsDto);
    public boolean deleteProjectById(long projectId);
}
