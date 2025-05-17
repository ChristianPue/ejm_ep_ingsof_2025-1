package com.company.taskmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateEmailException.class)
    public ProblemDetail handleDuplicateEmail(DuplicateEmailException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problem.setTitle("Correo duplicado");
        return problem;
    }

    @ExceptionHandler(InvalidEmailFormatException.class)
    public ProblemDetail handleInvalidEmailFormat(InvalidEmailFormatException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problem.setTitle("Formato de correo inválido");
        return problem;
    }

    @ExceptionHandler(InvalidSeniorityException.class)
    public ProblemDetail handleInvalidSeniority(InvalidSeniorityException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problem.setTitle("Seniority no permitido");
        return problem;
    }


    @ExceptionHandler(InvalidDueDateException.class)
    public ProblemDetail handleInvalidDueDateException(InvalidDueDateException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problem.setTitle("La fecha límite debe ser futura");
        return problem;
    }

    @ExceptionHandler(TaskAlreadyCompletedException.class)
    public ProblemDetail handleTaskAlreadyCompleted(TaskAlreadyCompletedException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problem.setTitle("Tarea ya completada");
        return problem;
    }

    @ExceptionHandler(MaxActiveTasksReachedException.class)
    public ProblemDetail handleMaxActiveTasksReached(MaxActiveTasksReachedException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problem.setTitle("Máximo de tareas activas alcanzado");
        return problem;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFound(ResourceNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Recurso no encontrado");
        return problem;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        problem.setTitle("Error interno del servidor");
        return problem;
    }
}
