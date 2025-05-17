package com.company.taskmanagement.dto.request;

import com.company.taskmanagement.model.enums.Seniority;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CreateDeveloperRequest(
        @NotBlank(message = "El nombre es obligatorio")
        String name,

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "Formato de correo inválido")
        String email,

        @NotNull(message = "El seniority es obligatorio")
        Seniority seniority
) {}