package com.codewithsuraj.taskflow.exception;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(Long id) {
        super("Project not found with id: " + id);
    }
}
