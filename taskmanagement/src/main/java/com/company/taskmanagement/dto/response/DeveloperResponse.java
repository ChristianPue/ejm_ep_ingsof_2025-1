package com.company.taskmanagement.dto.response;

import com.company.taskmanagement.model.enums.Seniority;

public record DeveloperResponse(
        Long id,
        String name,
        String email,
        Seniority seniority
) {}