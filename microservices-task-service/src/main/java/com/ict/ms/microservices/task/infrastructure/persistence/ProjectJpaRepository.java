package com.ict.ms.microservices.task.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ict.ms.microservices.task.domain.Project;

public interface ProjectJpaRepository extends JpaRepository<Project, Long> {
	Optional<Project> findById(Long id);
}
