package com.codewithsuraj.taskflow.repository;

import com.codewithsuraj.taskflow.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
