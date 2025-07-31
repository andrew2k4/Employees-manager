package com.Andrew.service.booking.user.services;

import com.Andrew.service.booking.task.Task;
import com.Andrew.service.booking.task.dto.TaskDto;
import com.Andrew.service.booking.user.User;
import com.Andrew.service.booking.user.UserRepository;
import com.Andrew.service.booking.user.dto.SignupRequestDTO;
import com.Andrew.service.booking.user.dto.UserDashboardDto;
import com.Andrew.service.booking.user.dto.UserDto;
import com.Andrew.service.booking.user.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, AuthService {

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





    @Override
    public List<UserDashboardDto> getUserDashboardDto(){
        return userRepository.findAll().stream().map(User::getDashboardDto).toList();
    }



    @Override
    public List<UserDashboardDto> getUserDashboardFilteredDto(String startDate, String endDate) {
        try {
            List<User> users = userRepository.findUsersWithTasks(LocalDateTime.parse(startDate), LocalDateTime.parse(endDate));
            List<UserDashboardDto> userDashboardDtos =  users.stream()
                    .map(user -> {
                        List<TaskDto> tasks = user.getTasks().stream()
                                .filter(t -> t.getAddedTime().isAfter(LocalDateTime.now().minusDays(1)) && t.getAddedTime().isBefore(LocalDateTime.now()))
                                .map(Task::getDto)
                                .collect(Collectors.toList());
                        return new UserDashboardDto(user.getId(), user.getName(), tasks);
                    })
                    .toList();
            return userDashboardDtos;
        } catch (Exception e) {
            e.printStackTrace(); // Affiche l'erreur pour le débogage
            throw new RuntimeException("Error fetching user dashboard data", e);
        }
    }

    @Override
    public UserDashboardDto getUserById(long id){
        Optional<User> optionalUser= userRepository.findById(id);
        return optionalUser.map(User::getDashboardDto).orElse(null);
    }
}
