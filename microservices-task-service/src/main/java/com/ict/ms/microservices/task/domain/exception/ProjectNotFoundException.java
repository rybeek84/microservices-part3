package com.ict.ms.microservices.task.domain.exception;

public class ProjectNotFoundException extends RuntimeException {

    public ProjectNotFoundException(Long id){
        super("There is no project in database for given id: " + id);
    }
}
