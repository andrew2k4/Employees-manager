package com.Andrew.service.booking.services.authentication;

import com.Andrew.service.booking.Repository.UserRepository;
import com.Andrew.service.booking.dto.SignupRequestDTO;
import com.Andrew.service.booking.dto.UserDto;
import com.Andrew.service.booking.entity.User;
import com.Andrew.service.booking.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    public UserDto signupClient(SignupRequestDTO signupRequestDTO){
        User user = new User();


        user.setName(signupRequestDTO.getName());
        user.setLastName(signupRequestDTO.getLastName());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPhone(signupRequestDTO.getPhone());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));

        user.setRole(UserRole.EMPLOYEE);

        return userRepository.save(user).getDto();
    }

    public UserDto signupCompany(SignupRequestDTO signupRequestDTO){
        User user = new User();


        user.setName(signupRequestDTO.getName());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPhone(signupRequestDTO.getPhone());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));
        user.setRole(UserRole.MANAGER);

        return userRepository.save(user).getDto();
    }


    public Boolean presentByEmail(String email){
        return userRepository.findFirstByEmail(email) != null;
    }
}
