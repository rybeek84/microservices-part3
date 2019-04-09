package com.ict.ms.microservices.user.domain.exception;

import com.ict.ms.microservices.user.domain.vo.UserEmail;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(UserEmail userEmail) {
		super("There is no user in database for given email: " + userEmail.getEmail());
	}
}
