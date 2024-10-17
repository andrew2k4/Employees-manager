package com.Andrew.service.booking.services.Manager;

import com.Andrew.service.booking.Repository.ProjectRepository;
import com.Andrew.service.booking.Repository.TaskRepository;
import com.Andrew.service.booking.Repository.UserRepository;
import com.Andrew.service.booking.dto.projectdtos.DashboardDto;
import com.Andrew.service.booking.dto.projectdtos.ProjectDto;
import com.Andrew.service.booking.dto.taskDtos.TaskDto;
import com.Andrew.service.booking.entity.Project;
import com.Andrew.service.booking.entity.Task;
import com.Andrew.service.booking.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public boolean postProject(long userId, DashboardDto DashboardDto) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            Project project = new Project();

            project.setProjectName(DashboardDto.getProjectName());
            project.setClientName(DashboardDto.getClientName());
            project.setDescription(DashboardDto.getDetails());
            project.setCreatedAt(LocalDateTime.now());
            project.setUpdatedAt(LocalDateTime.now());


            projectRepository.save(project);
            return true;
        }

        return false;
    }

    @Override
    @Transactional
    public List<DashboardDto> getDashboard() {
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



    @Override
    public boolean postTask(long userId,TaskDto taskDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Project> optionalProject = projectRepository.findById((long) taskDto.getProjectId());

        if (optionalUser.isPresent() && optionalProject.isPresent()) {
            Task task = new Task();
            User user = optionalUser.get(); // Directly using the Optional value

            task.setDescription(taskDto.getDescription());
            task.setAddedTime(LocalDateTime.now()); // Use LocalDateTime instead of Date
            task.setHours(taskDto.getHours());
            task.setUser(user); // Assign the User object directly to the task
            task.setProject(optionalProject.get());

            // If assigning the user to the project is required, otherwise remove it
            Project project = optionalProject.get();
            project.setUpdatedAt(LocalDateTime.now());
            projectRepository.save(project);
            if (!user.getProjects().contains(project)) {
                user.getProjects().add(project);
                userRepository.save(user); // Save only if a new project is added
            }
            projectRepository.save(project);
            taskRepository.save(task);
            return true;
        }

        return false;
    }


    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream().map(Task::getDto).collect(Collectors.toList());
    }

    @Override
    public TaskDto getTaskById(long taskId) {
        Optional<Task> optionalTask = taskRepository.findById((int) taskId);

        return optionalTask.map(Task::getDto).orElse(null);
    }




}
