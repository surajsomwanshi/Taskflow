package com.codewithsuraj.taskflow.service;

import com.codewithsuraj.taskflow.controller.TaskController;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private final List<TaskController.Task> tasks = List.of(
            new TaskController.Task(1L,"Learn Inversion of control", true),
            new TaskController.Task(1L,"Learn understand dependency injection", false),
            new TaskController.Task(1L,"Learn AOP", false)

    );


    @Override
    public List<TaskController.Task> getAllTasks() {
        return tasks;
    }
}
