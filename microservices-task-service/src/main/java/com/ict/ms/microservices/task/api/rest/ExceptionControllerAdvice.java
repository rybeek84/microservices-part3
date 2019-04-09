package com.ict.ms.microservices.task.api.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ict.ms.microservices.task.api.rest.dto.ApiError;
import com.ict.ms.microservices.task.domain.exception.InvalidProjectMemberException;
import com.ict.ms.microservices.task.domain.exception.InvalidStatusException;
import com.ict.ms.microservices.task.domain.exception.ProjectNotFoundException;
import com.ict.ms.microservices.task.domain.exception.UserNotFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(ProjectNotFoundException.class)
	public ResponseEntity<ApiError> handleMissingProject(ProjectNotFoundException exception) {
		ApiError error = getApiError(HttpStatus.NOT_FOUND, exception.getLocalizedMessage());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidProjectMemberException.class)
	public ResponseEntity<ApiError> handleMissingProject(InvalidProjectMemberException exception) {
		ApiError error = getApiError(HttpStatus.FORBIDDEN, exception.getLocalizedMessage());

		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiError> handleMissingUser(InvalidProjectMemberException exception) {
		ApiError error = getApiError(HttpStatus.NOT_FOUND, exception.getLocalizedMessage());

		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(InvalidStatusException.class)
	public ResponseEntity<ApiError> handleInvalidStatus(InvalidStatusException ex) {
		ApiError error = getApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	private ApiError getApiError(HttpStatus forbidden, String localizedMessage) {
		return ApiError.builder().status(forbidden).message(localizedMessage).build();
	}

}
