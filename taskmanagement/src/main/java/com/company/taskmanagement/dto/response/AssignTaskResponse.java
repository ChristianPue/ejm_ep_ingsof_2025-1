package com.company.taskmanagement.dto.response;

import java.time.LocalDateTime;

public record AssignTaskResponse(
        String message,
        Long taskId,
        Long developerId,
        LocalDateTime assignedAt
) {}