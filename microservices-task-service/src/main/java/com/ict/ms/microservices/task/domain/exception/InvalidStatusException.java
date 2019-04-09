package com.ict.ms.microservices.task.domain.exception;

public class InvalidStatusException extends RuntimeException {
    public InvalidStatusException(String value) {
        super("Invalid status: '" + value );
    }
}
