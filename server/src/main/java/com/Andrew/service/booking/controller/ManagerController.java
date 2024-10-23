package com.Andrew.service.booking.controller;

import com.Andrew.service.booking.Repository.TaskRepository;
import com.Andrew.service.booking.dto.projectdtos.DashboardDto;
import com.Andrew.service.booking.dto.taskDtos.TaskDto;
import com.Andrew.service.booking.dto.userDtos.UserDashboardDto;
import com.Andrew.service.booking.services.Manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
   public ResponseEntity<?> postProject(@PathVariable long userId, @RequestBody DashboardDto projectDto){


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
        return ResponseEntity.ok(managerService.getAllProject());
    }

    @GetMapping({"/projects/dashboard"})
    public ResponseEntity<?> getDashboard(){
        return ResponseEntity.ok(managerService.getDashboard());
    }

    @GetMapping({"/project/{projectId}"})
    public ResponseEntity<?> getProjectById(@PathVariable Long projectId){
        DashboardDto projectDto = managerService.getProjectById(projectId);

        if(projectDto != null){
            return ResponseEntity.ok(projectDto);
        }
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }


    @PostMapping({"/task/{userId}"})
    public ResponseEntity<TaskDto> createTask(@PathVariable long userId, @RequestBody TaskDto taskDto) {
        System.out.print(taskDto);
        boolean isCreated = managerService.postTask(userId , taskDto);
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


    @GetMapping({"/employee/dashboard"})
    public ResponseEntity<?> getUserDashboard(){
        List<UserDashboardDto> userDashboardDtos = managerService.getUserDashboardDto();
        return ResponseEntity.ok(userDashboardDtos);
    }

    @GetMapping({"/employee/dashboard/{startDate}/{endDate}"})
    public ResponseEntity<?> getUserDashboardFiltered(@PathVariable String startDate, @PathVariable String endDate){

        return ResponseEntity.ok(managerService.getUserDashboardFilteredDto(startDate,endDate));
    }




}
