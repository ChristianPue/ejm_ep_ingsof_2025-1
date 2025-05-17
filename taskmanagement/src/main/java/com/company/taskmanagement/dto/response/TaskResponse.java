package com.company.taskmanagement.dto.response;

import com.company.taskmanagement.model.enums.Priority;
import com.company.taskmanagement.model.enums.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskResponse(
        Long id,
        String title,
        String description,
        LocalDate dueDate,
        Priority priority,
        TaskStatus status,
        Long developerId,
        LocalDateTime assignedAt,
        String assignedBy
) {}