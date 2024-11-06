package com.Andrew.service.booking.user.dto;

import com.Andrew.service.booking.task.dto.TaskDto;
import com.Andrew.service.booking.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
