package com.Andrew.service.booking.user;

import com.Andrew.service.booking.user.dto.UserDashboardDto;
import com.Andrew.service.booking.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({"/employee/dashboard"})
    public ResponseEntity<?> getUserDashboard(){
        List<UserDashboardDto> userDashboardDtos = userService.getUserDashboardDto();
        return ResponseEntity.ok(userDashboardDtos);
    }

    @GetMapping({"/dashboard/{startDate}/{endDate}"})
    public ResponseEntity<?> getUserDashboardFiltered(@PathVariable String startDate, @PathVariable String endDate){

        return ResponseEntity.ok(userService.getUserDashboardFilteredDto(startDate,endDate));
    }


}
