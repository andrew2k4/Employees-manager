package com.Andrew.service.booking.dto.projectdtos;

import lombok.Data;

@Data
public class ProjectDetailsDto {
    private long id;

    private String projectName;
    private String clientName;

    private String description;

}
