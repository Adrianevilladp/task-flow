package com.adrian.taskflow.domain.repository;

import com.adrian.taskflow.domain.model.Project;
import com.adrian.taskflow.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByOwner(User owner);

    @Query("""
    SELECT p FROM Project p
    JOIN p.members m
    WHERE p.owner.id = :userId OR m.id = :userId
""")
    List<Project> findAllByUser(Long userId);
}
