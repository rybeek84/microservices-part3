package com.ict.ms.microservices.task.domain.exception;

import java.util.UUID;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(UUID uuid) {
        super("There is no task with uuid: " + uuid);
    }
}
