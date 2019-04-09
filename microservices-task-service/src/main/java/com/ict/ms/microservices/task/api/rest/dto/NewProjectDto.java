package com.ict.ms.microservices.task.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class NewProjectDto {

    private String name;

    @JsonCreator
    public NewProjectDto(@JsonProperty("name") String name) {
        this.name = name;
    }
}
