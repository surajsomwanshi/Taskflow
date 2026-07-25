package com.codewithsuraj.taskflow.service;

import com.codewithsuraj.taskflow.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> findAll();

    Optional<Task> findById(Long id);

    Task create(TaskServiceImpl.TaskRequest request);

    Optional<Task> replace(Long id, TaskServiceImpl.TaskRequest request);

    void delete(Long id);
}
