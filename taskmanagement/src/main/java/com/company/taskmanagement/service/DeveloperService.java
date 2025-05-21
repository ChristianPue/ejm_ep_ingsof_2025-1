package com.company.taskmanagement.service;

import com.company.taskmanagement.dto.request.CreateDeveloperRequest;
import com.company.taskmanagement.dto.response.DeveloperResponse;
import com.company.taskmanagement.exception.DuplicateEmailException;
import com.company.taskmanagement.mapper.DeveloperMapper;
import com.company.taskmanagement.model.Developer;
import com.company.taskmanagement.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperResponse registerDeveloper(CreateDeveloperRequest request) {
        if (developerRepository.existsByEmailJPQL(request.email())) {
            throw new DuplicateEmailException("El correo ya está registrado");
        }

        Developer developer = DeveloperMapper.toEntity(request);
        Developer saved = developerRepository.save(developer);
        return DeveloperMapper.toResponse(saved);
    }


}
