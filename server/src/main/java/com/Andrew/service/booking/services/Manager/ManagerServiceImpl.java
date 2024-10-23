package com.Andrew.service.booking.services.Manager;

import com.Andrew.service.booking.Repository.ProjectRepository;
import com.Andrew.service.booking.Repository.TaskRepository;
import com.Andrew.service.booking.Repository.UserRepository;
import com.Andrew.service.booking.dto.projectdtos.DashboardDto;
import com.Andrew.service.booking.dto.projectdtos.ProjectDetailsDto;
import com.Andrew.service.booking.dto.projectdtos.ProjectDto;
import com.Andrew.service.booking.dto.taskDtos.TaskDto;
import com.Andrew.service.booking.dto.userDtos.UserDashboardDto;
import com.Andrew.service.booking.entity.Project;
import com.Andrew.service.booking.entity.Task;
import com.Andrew.service.booking.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
            project.setDescription(DashboardDto.getDescription());
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
    public boolean updateProjectById(long projectId, ProjectDetailsDto projectDetailsDto) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();

            boolean isUpdated = false;

            // Update fields if they are different
            if (!project.getProjectName().equals(projectDetailsDto.getProjectName())) {
                project.setProjectName(projectDetailsDto.getProjectName());
                isUpdated = true;
            }

            if (!project.getClientName().equals(projectDetailsDto.getClientName())) {
                project.setClientName(projectDetailsDto.getClientName());
                isUpdated = true;
            }

            if (!project.getDescription().equals(projectDetailsDto.getDescription())) {
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



    @Override
    public DashboardDto getProjectById(long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        return optionalProject.map(Project::getDto).orElse(null);
    }

    @Override
    public boolean deleteProjectById(long projectId) {
        // Vérifier si le projet existe
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        if (optionalProject.isPresent()) {
            // Supprimer le projet et toutes les tâches associées
            projectRepository.deleteById(projectId);
            return true;  // Suppression réussie
        }

        return false; // Projet non trouvé
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
        Optional<Task> optionalTask = taskRepository.findById(taskId);

        return optionalTask.map(Task::getDto).orElse(null);
    }



    @Override
    public List<UserDashboardDto> getUserDashboardDto(){
        return userRepository.findAll().stream().map(User::getDashboardDto).toList();
    }



    @Override
    public List<UserDashboardDto> getUserDashboardFilteredDto(String startDate, String endDate) {
        try {
            List<User> users = userRepository.findUsersWithTasks(LocalDateTime.parse(startDate), LocalDateTime.parse(endDate));
            List<UserDashboardDto> userDashboardDtos =  users.stream()
                    .map(user -> {
                        List<TaskDto> tasks = user.getTasks().stream()
                                .filter(t -> t.getAddedTime().isAfter(LocalDateTime.now().minusDays(1)) && t.getAddedTime().isBefore(LocalDateTime.now()))
                                .map(Task::getDto)
                                .collect(Collectors.toList());
                        return new UserDashboardDto(user.getId(), user.getName(), tasks);
                    })
                    .toList();
            return userDashboardDtos;
        } catch (Exception e) {
            e.printStackTrace(); // Affiche l'erreur pour le débogage
            throw new RuntimeException("Error fetching user dashboard data", e);
        }
    }

    @Override
    public UserDashboardDto getUserById(long id){
        Optional<User> optionalUser= userRepository.findById(id);
        return optionalUser.map(User::getDashboardDto).orElse(null);
    }


    @Override
    public boolean updateTask(long taskId, TaskDto taskDto) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();

            boolean isUpdated = false;

            // Vérifier les changements et mettre à jour les champs
            if (!task.getDescription().equals(taskDto.getDescription())) {
                task.setDescription(taskDto.getDescription());
                isUpdated = true;
            }

            if (task.getHours() != taskDto.getHours()) {
                task.setHours(taskDto.getHours());
                isUpdated = true;
            }

            if (taskDto.getAddedTime() != null && !task.getAddedTime().equals(taskDto.getAddedTime())) {
                task.setAddedTime(taskDto.getAddedTime());
                isUpdated = true;
            }


            if (isUpdated) {
                taskRepository.save(task);
            }

            return isUpdated;
        }

        return false;
    }
    @Override
    public boolean deleteTaskById(long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);

        if (optionalTask.isPresent()) {
            taskRepository.deleteById(taskId);
            return true;
        }

        return false; // La tâche n'existe pas
    }









}
