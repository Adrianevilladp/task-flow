package com.adrian.taskflow.domain.repository;

import com.adrian.taskflow.domain.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Project, Long> {
}
