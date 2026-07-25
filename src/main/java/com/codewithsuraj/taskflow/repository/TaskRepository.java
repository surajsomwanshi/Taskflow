package com.codewithsuraj.taskflow.repository;

import com.codewithsuraj.taskflow.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {

    //Derived queries accept a Pageable too, combining filtering and paging
    Page<Task> findByCompleted(boolean completed, Pageable pageable);

}
