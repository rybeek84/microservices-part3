package com.ict.ms.microservices.task.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.ms.microservices.task.infrastructure.persistence.ProjectJpaRepository;

@Service
public class ProjectDomainRepository {

	private ProjectJpaRepository projectJpaRepository;

	@Autowired
	public ProjectDomainRepository(ProjectJpaRepository projectJpaRepository) {
		this.projectJpaRepository = projectJpaRepository;
	}

	public Optional<Project> findById(Long id) {
		return projectJpaRepository.findById(id);
	}

	public Project save(Project project) {
		return projectJpaRepository.save(project);
	}

	public List<Project> findAll() {
		return projectJpaRepository.findAll();
	}

	public void delete(Long id) {
		projectJpaRepository.deleteById(id);
	}
}
