package com.Andrew.service.booking.services.authentication;

import com.Andrew.service.booking.dto.SignupRequestDTO;
import com.Andrew.service.booking.dto.UserDto;

public interface AuthService {
    UserDto signupClient(SignupRequestDTO signupRequestDTO);
    UserDto signupCompany(SignupRequestDTO signupRequestDTO);
    Boolean presentByEmail(String email);
}
