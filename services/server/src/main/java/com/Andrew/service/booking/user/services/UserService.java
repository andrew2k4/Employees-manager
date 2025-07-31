package com.Andrew.service.booking.user.services;

import com.Andrew.service.booking.user.dto.UserDashboardDto;

import java.util.List;

public interface UserService {
    List<UserDashboardDto> getUserDashboardDto();
    List<UserDashboardDto> getUserDashboardFilteredDto(String startDate, String endDate);
    UserDashboardDto getUserById(long id);
}
