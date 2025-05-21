package com.company.taskmanagement.service;

import com.company.taskmanagement.dto.request.AssignTaskRequest;
import com.company.taskmanagement.dto.request.CreateTaskRequest;
import com.company.taskmanagement.dto.response.AssignTaskResponse;
import com.company.taskmanagement.dto.response.TaskResponse;
import com.company.taskmanagement.exception.InvalidDueDateException;
import com.company.taskmanagement.exception.MaxActiveTasksReachedException;
import com.company.taskmanagement.exception.ResourceNotFoundException;
import com.company.taskmanagement.exception.TaskAlreadyCompletedException;
import com.company.taskmanagement.mapper.TaskMapper;
import com.company.taskmanagement.model.Developer;
import com.company.taskmanagement.model.Task;
import com.company.taskmanagement.model.enums.TaskStatus;
import com.company.taskmanagement.repository.DeveloperRepository;
import com.company.taskmanagement.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final DeveloperRepository developerRepository;

    public TaskResponse createTask(CreateTaskRequest request) {
        if (request.dueDate().isBefore(LocalDate.now())) {
            throw new InvalidDueDateException("La fecha límite debe ser futura");
        }

        Task task = TaskMapper.toEntity(request);
        Task saved = taskRepository.save(task);
        return TaskMapper.toResponse(saved);
    }

    public List<TaskResponse> getActiveTasksByDeveloper(Long developerId) {
        List<Task> activeTasks = taskRepository.findActiveTasks(developerId, List.of("PENDING", "IN_PROGRESS"));
        return activeTasks.stream().map(TaskMapper::toResponse).toList();
    }

    /**
     * Asigna una tarea a un developer, verificando reglas de negocio como:
     * - Tarea existente
     * - Developer existente
     * - Estado no COMPLETED
     * - Límite máximo de 5 tareas activas
     */
    public AssignTaskResponse assignTask(AssignTaskRequest request) {
        Task task = taskRepository.findById(request.taskId())
                .orElseThrow(() -> new ResourceNotFoundException("La tarea no existe"));

        Developer developer = developerRepository.findById(request.developerId())
                .orElseThrow(() -> new ResourceNotFoundException("El developer no existe"));

        if (task.getStatus() == TaskStatus.COMPLETED) {
            throw new TaskAlreadyCompletedException("No se puede asignar una tarea finalizada");
        }

        long activeTasks = taskRepository.countActiveTasks(developer.getId(), List.of(TaskStatus.PENDING, TaskStatus.IN_PROGRESS));
        if (activeTasks >= 5) {
            throw new MaxActiveTasksReachedException("El developer ya tiene el máximo de tareas activas permitido");
        }

        task.setDeveloper(developer);
        task.setStatus(TaskStatus.IN_PROGRESS);
        task.setAssignedAt(LocalDateTime.now());
        task.setAssignedBy(request.assignedBy());

        Task updated = taskRepository.save(task);
        return TaskMapper.toAssignResponse(updated);
    }
}