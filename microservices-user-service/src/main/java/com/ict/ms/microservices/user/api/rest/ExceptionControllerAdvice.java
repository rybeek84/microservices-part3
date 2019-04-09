package com.ict.ms.microservices.user.api.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ict.ms.microservices.user.api.rest.dto.ApiError;
import com.ict.ms.microservices.user.domain.exception.UserNotFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiError> handleMissingProject(UserNotFoundException exception) {

		ApiError error = getApiError(HttpStatus.NOT_FOUND, exception.getLocalizedMessage());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	private ApiError getApiError(HttpStatus forbidden, String localizedMessage) {
		return ApiError.builder().status(forbidden).message(localizedMessage).build();
	}

}
