package com.ict.ms.microservices.task.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.ReadOnlyProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ict.ms.microservices.task.domain.vo.UserEmail;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Task {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	private Status status = Status.NEW;
	private LocalDateTime dueDate;

	@ReadOnlyProperty
	private UUID uuid;

	@AttributeOverride(name = "email", column = @Column(name = "author"))
	private UserEmail author;

	@AttributeOverride(name = "email", column = @Column(name = "assignedTo"))
	private UserEmail assignedTo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectId")
	@JsonIgnore
	private Project project;

	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments;

	Task() {

	}

	@Builder
	public Task(String name, String description, LocalDateTime dueDate, UserEmail author, UserEmail assignedTo) {
		this.name = name;
		this.description = description;
		this.dueDate = dueDate;
		this.author = author;
		this.assignedTo = assignedTo;
		this.uuid = UUID.randomUUID();
		this.comments = new ArrayList<>();
	}

	void addToProject(Project project) {
		this.project = project;
	}
}
