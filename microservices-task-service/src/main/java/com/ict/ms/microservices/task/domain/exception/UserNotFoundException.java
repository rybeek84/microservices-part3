package com.ict.ms.microservices.task.domain.exception;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String email) {
		super("There is no user with email: " + email);
	}
}
