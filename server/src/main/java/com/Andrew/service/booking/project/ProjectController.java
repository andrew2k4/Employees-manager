package com.Andrew.service.booking.project;


import com.Andrew.service.booking.project.dto.DashboardDto;
import com.Andrew.service.booking.project.dto.ProjectDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ProjectController {


    @Autowired
    private ProjectService projectService;



    // FOR ALL
    @GetMapping({"/projects"})
    public ResponseEntity<?> getAllProjects(){
        return ResponseEntity.ok(projectService.getAllProject());
    }

    @GetMapping({"/projects/dashboard"})
    public ResponseEntity<?> getDashboard(){
        return ResponseEntity.ok(projectService.getProjectDashboard());
    }

    @GetMapping({"/project/{projectId}"})
    public ResponseEntity<?> getProjectById(@PathVariable Long projectId){
        DashboardDto projectDto = projectService.getProjectById(projectId);

        if(projectDto != null){
            return ResponseEntity.ok(projectDto);
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }


    // For MANAGER
    @PostMapping("/manager/project/{userId}")
    public ResponseEntity<?> postProject(@PathVariable long userId, @RequestBody DashboardDto projectDto){


        boolean success = projectService.postProject(userId, projectDto);
        System.out.println( "2");

        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();

        }else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }

    @PutMapping({"/manager/project/{projectId}"})
    public ResponseEntity<?> UpdateProjectById(@PathVariable long projectId, @RequestBody ProjectDetailsDto projectDto){
        boolean isUpdate = projectService.updateProjectById(projectId, projectDto);
        if (!isUpdate){
            return   ResponseEntity.badRequest().build();
        }
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping({"/manager/project/{projectId}"})
    public ResponseEntity<?>  deleteProjectById(@PathVariable long projectId){
        boolean isDelete = projectService.deleteProjectById(projectId);

        if (!isDelete){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.accepted().build();
    }






}
