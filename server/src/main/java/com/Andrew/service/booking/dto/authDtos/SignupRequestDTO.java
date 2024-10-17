package com.Andrew.service.booking.dto.authDtos;

import com.Andrew.service.booking.enums.UserRole;
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
