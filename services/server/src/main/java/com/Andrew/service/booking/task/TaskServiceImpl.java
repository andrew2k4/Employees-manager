package com.Andrew.service.booking.task;

import com.Andrew.service.booking.project.Project;
import com.Andrew.service.booking.project.ProjectRepository;
import com.Andrew.service.booking.task.dto.TaskDto;
import com.Andrew.service.booking.user.User;
import com.Andrew.service.booking.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;


    // CREATE
    @Override
    public boolean postTask(long userId, TaskDto taskDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Project> optionalProject = projectRepository.findById((long) taskDto.getProjectId());

        if (optionalUser.isPresent() && optionalProject.isPresent()) {
            Task task = new Task();
            User user = optionalUser.get();

            task.setDescription(taskDto.getDescription());
            task.setAddedTime(LocalDateTime.now());
            task.setHours(taskDto.getHours());
            // Assign the User object directly to the task
            task.setUser(user);
            task.setProject(optionalProject.get());

            // If assigning the user to the project is required, otherwise remove it
            Project project = optionalProject.get();
            project.setUpdatedAt(LocalDateTime.now());
            projectRepository.save(project);
            if (!user.getProjects().contains(project)) {
                user.getProjects().add(project);
                // Save only if a new project is added
                userRepository.save(user);
            }
            projectRepository.save(project);
            taskRepository.save(task);
            return true;
        }

        return false;
    }

    // GET
    @Override
    public TaskDto getTaskById(long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);

        return optionalTask.map(Task::getDto).orElse(null);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream().map(Task::getDto).collect(Collectors.toList());
    }



    // UPDATE
    @Override
    public boolean updateTask(long taskId, TaskDto taskDto) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();

            boolean isUpdated = false;

            // Vérifier les changements et mettre à jour les champs
            if (task.getDescription() != null && task.getDescription().equals(taskDto.getDescription())){
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

    // DELETE
    @Override
    public boolean deleteTaskById(long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);

        if (optionalTask.isPresent()) {
            taskRepository.deleteById(taskId);
            return true;
        }

        return false;
    }

}
