package com.Andrew.service.booking.task;

import com.Andrew.service.booking.task.dto.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @PostMapping({"/task/{taskId}"})
    public ResponseEntity<TaskDto> createTask(@PathVariable long taskId, @RequestBody TaskDto taskDto) {

        boolean isCreated = taskService.postTask(taskId , taskDto);
        if (isCreated) {
            return ResponseEntity.ok(taskDto);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping({"/tasks"})
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping({"/task/{id}"})
    public ResponseEntity<TaskDto> getTaskById(@PathVariable long id) {
        TaskDto taskDto = taskService.getTaskById(id);
        if (taskDto != null) {
            return ResponseEntity.ok(taskDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/task/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable long taskId, @RequestBody TaskDto taskDto) {
        boolean isUpdate =  taskService.updateTask(taskId, taskDto);

        if(!isUpdate){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping({"/task/{taskId}"})
    public ResponseEntity<?> deleteTask(@PathVariable long taskId, @RequestBody TaskDto taskDto) {
        boolean isDelete =  taskService.deleteTaskById(taskId);

        if(!isDelete){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.accepted().build();
    }
}
