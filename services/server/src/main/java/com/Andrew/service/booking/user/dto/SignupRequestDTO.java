package com.Andrew.service.booking.user.dto;

import com.Andrew.service.booking.user.enums.UserRole;
import lombok.Data;

@Data
public class SignupRequestDTO {
    private long id;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private int phone;
    private UserRole role;
}
