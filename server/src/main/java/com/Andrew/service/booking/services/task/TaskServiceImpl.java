package com.Andrew.service.booking.services.task;

import com.Andrew.service.booking.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements  TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public double getTotalHoursByEmployeeId(int employeeId) {
        Double totalHours = 2.0;
        return totalHours != null ? totalHours : 0.0;
    }
}

