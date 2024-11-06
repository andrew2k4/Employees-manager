package com.Andrew.service.booking.user;

import com.Andrew.service.booking.user.dto.UserDashboardDto;
import com.Andrew.service.booking.project.Project;
import com.Andrew.service.booking.task.Task;
import com.Andrew.service.booking.user.dto.UserDto;
import com.Andrew.service.booking.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String password;

    private String name;

    private String lastName;

    private int phone;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToMany()
    @JoinTable(
            name = "user_projects", // Join table name
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<Task>();

    public UserDto getDto(){
        UserDto userDto = new UserDto();

        userDto.setId(id);
        userDto.setName(name);
        userDto.setLastName(lastName);
        userDto.setEmail(email);
        userDto.setTasks(tasks.stream().map(Task::getDto).collect(Collectors.toList()));

        return userDto;
    }


    public UserDashboardDto getDashboardDto(){
        UserDashboardDto userDashboardDto = new UserDashboardDto();

        userDashboardDto.setId(id);
        userDashboardDto.setName(name);
        userDashboardDto.setTasks(tasks.stream().map(Task::getDto).collect(Collectors.toList()));
        return userDashboardDto;
    }
}
