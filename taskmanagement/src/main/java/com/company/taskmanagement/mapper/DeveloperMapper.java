package com.company.taskmanagement.mapper;

import com.company.taskmanagement.dto.request.CreateDeveloperRequest;
import com.company.taskmanagement.dto.response.DeveloperResponse;
import com.company.taskmanagement.model.Developer;

public class DeveloperMapper {

    public static DeveloperResponse toResponse(Developer developer) {
        return new DeveloperResponse(
                developer.getId(),
                developer.getName(),
                developer.getEmail(),
                developer.getSeniority()
        );
    }

    public static Developer toEntity(CreateDeveloperRequest request) {
        return Developer.builder()
                .name(request.name())
                .email(request.email())
                .seniority(request.seniority())
                .build();
    }
}