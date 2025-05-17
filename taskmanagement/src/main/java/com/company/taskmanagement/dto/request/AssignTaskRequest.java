package com.company.taskmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AssignTaskRequest(
        @NotNull(message = "El ID del developer es obligatorio")
        Long developerId,

        @NotNull(message = "El ID de la tarea es obligatorio")
        Long taskId,

        @NotBlank(message = "El campo assignedBy es obligatorio")
        String assignedBy
) {}