package com.adrian.taskflow.domain.repository;

import com.adrian.taskflow.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
