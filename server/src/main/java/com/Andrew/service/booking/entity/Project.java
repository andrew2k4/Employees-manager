package com.Andrew.service.booking.entity;

import com.Andrew.service.booking.dto.ProjectDto;
import jakarta.persistence.*;
import lombok.Data;

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

    private String Client;

    private String Description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<Task>();

    @ManyToMany(mappedBy = "projects") // Inverse relationship in Project
    private List<User> users = new ArrayList<>();

    public ProjectDto getDto(){
        ProjectDto projectDto = new ProjectDto();

        projectDto.setId(id);
        projectDto.setProjectName(projectName);
        projectDto.setTasks(tasks.stream().map(Task::getDto).collect(Collectors.toList()));
        projectDto.setUsers(users.stream().map(User::getDto).collect(Collectors.toList()));

        return projectDto;
    }
}
