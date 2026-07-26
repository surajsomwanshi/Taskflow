package com.codewithsuraj.taskflow.service;

import com.codewithsuraj.taskflow.controller.TaskController;
import com.codewithsuraj.taskflow.entity.Task;
import com.codewithsuraj.taskflow.exception.TaskNotFoundException;
import com.codewithsuraj.taskflow.repository.TaskRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task create(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.title());
        task.setCompleted(request.completed());

        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> replace(Long id, TaskRequest request) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(request.title());
                    task.setCompleted(request.completed());

                    return taskRepository.save(task);
                });
    }

    @Override
    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }

        taskRepository.deleteById(id);
    }

    public record TaskRequest(String title,
                              boolean completed) {
    }
}
