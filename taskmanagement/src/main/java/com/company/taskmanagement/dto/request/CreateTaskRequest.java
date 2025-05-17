package com.company.taskmanagement.dto.request;

import com.company.taskmanagement.model.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateTaskRequest(
        @NotBlank(message = "El título es obligatorio")
        @Size(min = 5, message = "El título debe tener al menos 5 caracteres")
        String title,

        @NotBlank(message = "La descripción es obligatoria")
        String description,

        @NotNull(message = "La fecha límite es obligatoria")
        LocalDate dueDate,

        @NotNull(message = "La prioridad es obligatoria")
        Priority priority
) {}
