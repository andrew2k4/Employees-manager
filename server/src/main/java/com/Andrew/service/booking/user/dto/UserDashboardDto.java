package com.Andrew.service.booking.user.dto;

import com.Andrew.service.booking.task.dto.TaskDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDashboardDto {
    private long id;
    private String name;
    private List<TaskDto> tasks;
}
