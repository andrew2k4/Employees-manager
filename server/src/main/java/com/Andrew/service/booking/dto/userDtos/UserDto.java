package com.Andrew.service.booking.dto.userDtos;

import com.Andrew.service.booking.dto.taskDtos.TaskDto;
import com.Andrew.service.booking.entity.Task;
import com.Andrew.service.booking.enums.UserRole;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private long id;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private int phone;
    private UserRole role;
    private List<TaskDto> tasks;
}
