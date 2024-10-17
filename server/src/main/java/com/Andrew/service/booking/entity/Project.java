package com.Andrew.service.booking.entity;

import com.Andrew.service.booking.dto.projectdtos.DashboardDto;
import com.Andrew.service.booking.dto.projectdtos.ProjectDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "projects")
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String projectName;

    private String clientName;

    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<Task>();

    @ManyToMany(mappedBy = "projects") // Inverse relationship in Project
    private List<User> users = new ArrayList<>();

    public DashboardDto getDto(){
        DashboardDto DashboardDto = new DashboardDto();

        DashboardDto.setId(id);
        DashboardDto.setProjectName(projectName);
        DashboardDto.setClientName(clientName);
        DashboardDto.setCreatedAt(createdAt);
        DashboardDto.setUpdatedAt(updatedAt);
        DashboardDto.setUsers(users.stream().map(User::getDto).collect(Collectors.toList()));

        return DashboardDto;
    }

    public ProjectDto getProjectDto(){
        ProjectDto projectDto = new ProjectDto();

        projectDto.setId(id);
        projectDto.setProjectName(projectName);

        return projectDto;
    }



}
