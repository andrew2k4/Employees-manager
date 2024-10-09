package com.Andrew.service.booking.dto;

import com.Andrew.service.booking.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private int phone;
    private UserRole role;
}
