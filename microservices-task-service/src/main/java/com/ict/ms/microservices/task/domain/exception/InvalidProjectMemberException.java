package com.ict.ms.microservices.task.domain.exception;

public class InvalidProjectMemberException extends RuntimeException {
    public InvalidProjectMemberException(String userEmail) {
        super("User '" + userEmail + "' is not a member of selected project");
    }
}
