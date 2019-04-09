package com.ict.ms.microservices.user.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiError {
    HttpStatus status;
    String message;

    @JsonCreator
    @Builder
    private ApiError(
            @JsonProperty("status") HttpStatus status,
            @JsonProperty("message") String message) {
        this.status = status;
        this.message = message;
    }
}
