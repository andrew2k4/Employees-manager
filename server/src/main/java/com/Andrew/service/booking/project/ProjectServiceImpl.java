package com.Andrew.service.booking.project;

import com.Andrew.service.booking.project.dto.DashboardDto;
import com.Andrew.service.booking.project.dto.ProjectDetailsDto;
import com.Andrew.service.booking.project.dto.ProjectDto;
import com.Andrew.service.booking.user.User;
import com.Andrew.service.booking.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    // CREATE
    @Override
    public boolean postProject(long userId, DashboardDto DashboardDto) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            Project project = new Project();

            project.setProjectName(DashboardDto.getProjectName());
            project.setClientName(DashboardDto.getClientName());
            project.setDescription(DashboardDto.getDescription());
            project.setCreatedAt(LocalDateTime.now());
            project.setUpdatedAt(LocalDateTime.now());


            projectRepository.save(project);
            return true;
        }

        return false;
    }


    // GET
    @Override
    @Transactional
    public List<DashboardDto> getProjectDashboard() {
        return projectRepository.findAll().stream().map(Project::getDto).collect(Collectors.toList());
    }

    @Override
    public List<ProjectDto> getAllProject(){
        return projectRepository.findAll().stream().map(Project::getProjectDto).collect(Collectors.toList());
    }

    @Override
    public DashboardDto getProjectById(long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        return optionalProject.map(Project::getDto).orElse(null);
    }


    // UPDATE
    @Override
    public boolean updateProjectById(long projectId, ProjectDetailsDto projectDetailsDto) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();

            boolean isUpdated = false;

            // Update fields if they are different
            if ( project.getProjectName() != null && !project.getProjectName().equals(projectDetailsDto.getProjectName())) {
                project.setProjectName(projectDetailsDto.getProjectName());
                isUpdated = true;
            }

            if (project.getClientName()!= null && !project.getClientName().equals(projectDetailsDto.getClientName())) {
                project.setClientName(projectDetailsDto.getClientName());
                isUpdated = true;
            }

            if (project.getDescription()!=null && !project.getDescription().equals(projectDetailsDto.getDescription())) {
                project.setDescription(projectDetailsDto.getDescription());
                isUpdated = true;
            }

            // Only save if there are updates
            if (isUpdated) {
                projectRepository.save(project);
            }

            return isUpdated;
        }

        return false;
    }

    //DELETE
    @Override
    public boolean deleteProjectById(long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isPresent()) {
            optionalProject.get().getUsers().forEach(user -> {
                // Remove the project from each user's project list
                user.getProjects().remove(optionalProject.get());
                userRepository.save(user);
            });
            projectRepository.deleteById(projectId);
            return true;
        }

        return false;
    }
}
