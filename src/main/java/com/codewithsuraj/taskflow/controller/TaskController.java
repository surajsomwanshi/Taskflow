package com.codewithsuraj.taskflow.controller;

import com.codewithsuraj.taskflow.dto.TaskResponse;
import com.codewithsuraj.taskflow.entity.Task;
import com.codewithsuraj.taskflow.service.TaskService;
import com.codewithsuraj.taskflow.service.TaskServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    final public TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //GET /task - return the whole collection
    @GetMapping
    public List<TaskResponse> list(){
        return taskService.findAll().stream()
                .map(TaskResponse::from)
                .toList();
    }

    // GET /tasks/{id} - return one task or 404 if it does not exist
    @GetMapping("/{id}")
    public ResponseEntity<Task> getOne(@PathVariable Long id){
        return taskService.findById(id)
                .map(ResponseEntity::ok) // 200 with the task
                .orElse(ResponseEntity.notFound().build()); // 404 if not present
    }

    // POST /tasks - create a task, return 201 with a Location header
    @PostMapping
    public ResponseEntity<Task> create(@RequestBody TaskServiceImpl.TaskRequest request){
        Task created = taskService.create(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    //PUT /tasks/{id} - replace a task entirely or 404 if its does not exist
    @PutMapping("/{id}")
    public ResponseEntity<Task> replace(@PathVariable Long id,
                                        @RequestBody TaskServiceImpl.TaskRequest request){
        return taskService.replace(id,request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        taskService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
