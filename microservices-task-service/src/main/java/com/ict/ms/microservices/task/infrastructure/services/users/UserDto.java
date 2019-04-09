package com.ict.ms.microservices.task.infrastructure.services.users;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.ict.ms.microservices.task.domain.vo.UserEmail;

import lombok.Data;

@Entity
@Data
public class UserDto {

	@EmbeddedId
	private UserEmail userId;
	private String firstName;
	private String lastName;
}
