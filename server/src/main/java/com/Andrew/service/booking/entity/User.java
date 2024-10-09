package com.Andrew.service.booking.entity;

import com.Andrew.service.booking.dto.UserDto;
import com.Andrew.service.booking.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
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

    @ManyToMany
    @JoinTable(
            name = "user_projects", // Join table name
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects = new ArrayList<>();

    public UserDto getDto(){
        UserDto userDto = new UserDto();

        userDto.setId(id);
        userDto.setName(name);
        userDto.setLastName(lastName);
        userDto.setEmail(email);

        return userDto;
    }
}
