package com.company.taskmanagement.controller;

import com.company.taskmanagement.dto.request.AssignTaskRequest;
import com.company.taskmanagement.dto.request.CreateTaskRequest;
import com.company.taskmanagement.dto.response.AssignTaskResponse;
import com.company.taskmanagement.dto.response.TaskResponse;
import com.company.taskmanagement.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request) {
        TaskResponse response = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/assign")
    public ResponseEntity<AssignTaskResponse> assignTask(@Valid @RequestBody AssignTaskRequest request) {
        AssignTaskResponse response = taskService.assignTask(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/developer/{developerId}/active")
    public ResponseEntity<List<TaskResponse>> getActiveTasks(@PathVariable Long developerId) {
        List<TaskResponse> activeTasks = taskService.getActiveTasksByDeveloper(developerId);
        return ResponseEntity.ok(activeTasks);
    }
}
