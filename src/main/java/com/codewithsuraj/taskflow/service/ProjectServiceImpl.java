package com.codewithsuraj.taskflow.service;

import com.codewithsuraj.taskflow.entity.Project;
import com.codewithsuraj.taskflow.entity.Task;
import com.codewithsuraj.taskflow.exception.ProjectNotFoundException;
import com.codewithsuraj.taskflow.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    //Move every task from one project to another, atomically. If any step
     //fails, no tasks move at all — the database stays consistent.

    @Transactional
    public void moveAllTasks(Long fromProjectId, Long toProjectId) {
        Project from = projectRepository.findById(fromProjectId)
                .orElseThrow(() -> new ProjectNotFoundException(fromProjectId));
        Project to = projectRepository.findById(toProjectId)
                .orElseThrow(() -> new ProjectNotFoundException(toProjectId));

        // Reassign each task to the new project (the owning side updates the FK).
        List<Task> tasks = List.copyOf(from.getTasks());
        for (Task task : tasks) {
            from.getTasks().remove(task);
            to.addTask(task);   // sets task.project = to (owning side)
        }
        // Dirty checking flushes the updates at commit — one atomic unit.
    }
}
