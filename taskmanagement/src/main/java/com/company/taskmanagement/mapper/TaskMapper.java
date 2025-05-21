package com.company.taskmanagement.mapper;

import com.company.taskmanagement.dto.request.CreateTaskRequest;
import com.company.taskmanagement.dto.response.AssignTaskResponse;
import com.company.taskmanagement.dto.response.TaskResponse;
import com.company.taskmanagement.model.Task;

public class TaskMapper {

    public static TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus(),
                task.getDeveloper() != null ? task.getDeveloper().getId() : null,
                task.getAssignedAt(),
                task.getAssignedBy()
        );
    }

    public static Task toEntity(CreateTaskRequest request) {
        return Task.builder()
                .title(request.title())
                .description(request.description())
                .dueDate(request.dueDate())
                .priority(request.priority())
                .status(com.company.taskmanagement.model.enums.TaskStatus.PENDING)
                .build();
    }

    public static AssignTaskResponse toAssignResponse(Task task) {
        return new AssignTaskResponse(
                "Tarea asignada correctamente al developer",
                task.getId(),
                task.getDeveloper() != null ? task.getDeveloper().getId() : null,
                task.getAssignedAt()
        );
    }
}
