package com.company.taskmanagement.exception;

public class MaxActiveTasksReachedException extends RuntimeException {
    public MaxActiveTasksReachedException(String message) {
        super(message);
    }
}