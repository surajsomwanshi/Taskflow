package com.codewithsuraj.taskflow.dto;

import com.codewithsuraj.taskflow.controller.TaskController;
import com.codewithsuraj.taskflow.entity.Task;

public record TaskResponse(Long id, String title, boolean completed, String status) {
    public static TaskResponse from(Task task){
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.isCompleted(),
                task.isCompleted() ? "DONE" : "PENDING"
        );
    }

}
