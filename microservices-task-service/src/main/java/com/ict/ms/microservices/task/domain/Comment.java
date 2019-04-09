package com.ict.ms.microservices.task.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ict.ms.microservices.task.domain.vo.UserEmail;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class Comment {
	@Id
	@GeneratedValue
	private Long id;
	private LocalDateTime createdAt;
	private UserEmail createdBy;
	private String text;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Task task;
}
