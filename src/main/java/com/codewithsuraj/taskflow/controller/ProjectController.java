package com.codewithsuraj.taskflow.controller;

import com.codewithsuraj.taskflow.service.ProjectService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    final public ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


}
