package com.codewithsuraj.taskflow.service;

import com.codewithsuraj.taskflow.controller.TaskController;

import java.util.List;

public interface TaskService {
    List<TaskController.Task> getAllTasks();
}
