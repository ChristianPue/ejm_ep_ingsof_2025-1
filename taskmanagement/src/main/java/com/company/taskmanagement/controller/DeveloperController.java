package com.company.taskmanagement.controller;


import com.company.taskmanagement.dto.request.CreateDeveloperRequest;
import com.company.taskmanagement.dto.response.DeveloperResponse;
import com.company.taskmanagement.service.DeveloperService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/developers")
@RequiredArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;


    @PostMapping
    public ResponseEntity<DeveloperResponse> registerDeveloper(@Valid @RequestBody CreateDeveloperRequest request) {
        DeveloperResponse response = developerService.registerDeveloper(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}