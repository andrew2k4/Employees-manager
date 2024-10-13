package com.Andrew.service.booking.controller;

import com.Andrew.service.booking.Repository.TaskRepository;
import com.Andrew.service.booking.dto.ProjectDto;
import com.Andrew.service.booking.dto.TaskDto;
import com.Andrew.service.booking.services.Manager.ManagerService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ManagerService managerService;

    @PostMapping("/project/{userId}")
   public ResponseEntity<?> postProject(@PathVariable long userId, @RequestBody ProjectDto projectDto){
        System.out.println( projectDto);
        System.out.println( userId);

       boolean success = managerService.postProject(userId, projectDto);
        System.out.println( "2");

       if(success){
           return ResponseEntity.status(HttpStatus.OK).build();

       }else {
           return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();

       }
   }


    @GetMapping({"/projects"})
    public ResponseEntity<?> getAllProjects(){
        return ResponseEntity.ok(managerService.getAllProjects());
    }


    @GetMapping({"/project/{projectId}"})
    public ResponseEntity<?> getProjectById(@PathVariable Long projectId){
        ProjectDto projectDto = managerService.getProjectById(projectId);

        if(projectDto != null){
            return ResponseEntity.ok(projectDto);
        }
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }




    @PostMapping({"/task"})
    public ResponseEntity<TaskDto> createTask( @RequestBody TaskDto taskDto) {
        System.out.print(taskDto);
        boolean isCreated = managerService.postTask(taskDto);
        if (isCreated) {
            return ResponseEntity.ok(taskDto);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping({"/tasks"})
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> tasks = managerService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping({"/task/{id}"})
    public ResponseEntity<TaskDto> getTaskById(@PathVariable long id) {
        TaskDto taskDto = managerService.getTaskById(id);
        if (taskDto != null) {
            return ResponseEntity.ok(taskDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
