package com.Andrew.service.booking.user.services;

import com.Andrew.service.booking.user.dto.SignupRequestDTO;
import com.Andrew.service.booking.user.dto.UserDto;

public interface AuthService {
    UserDto signupClient(SignupRequestDTO signupRequestDTO);
    UserDto signupCompany(SignupRequestDTO signupRequestDTO);
    Boolean presentByEmail(String email);
}
