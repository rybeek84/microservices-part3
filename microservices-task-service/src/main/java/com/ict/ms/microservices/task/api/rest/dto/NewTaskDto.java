package com.ict.ms.microservices.task.api.rest.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ict.ms.microservices.task.domain.vo.UserEmail;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NewTaskDto {
    private String name;
    private String description;
    private UserEmail assignedTo;
    private LocalDateTime dueDate;

    @JsonCreator
    NewTaskDto(){}

    @Builder
    private NewTaskDto(String name, String description, UserEmail assignedTo, LocalDateTime dueDate) {
        this.name = name;
        this.description = description;
        this.assignedTo = assignedTo;
        this.dueDate = dueDate;
    }
}
