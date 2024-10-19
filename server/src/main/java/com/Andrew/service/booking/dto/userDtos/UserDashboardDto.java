package com.Andrew.service.booking.dto.userDtos;

import com.Andrew.service.booking.dto.taskDtos.TaskDto;
import com.Andrew.service.booking.entity.Task;
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
