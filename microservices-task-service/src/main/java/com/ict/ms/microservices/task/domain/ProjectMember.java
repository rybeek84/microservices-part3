package com.ict.ms.microservices.task.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ict.ms.microservices.task.domain.vo.UserEmail;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class ProjectMember {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JsonIgnore
	private Project project;

	private UserEmail user;

	ProjectMember() {
	}

	@Builder
	private ProjectMember(Project project, UserEmail user) {
		this.project = project;
		this.user = user;
	}

	protected boolean emailMatch(String email) {
		return user.getEmail().equalsIgnoreCase(email);
	}

}
