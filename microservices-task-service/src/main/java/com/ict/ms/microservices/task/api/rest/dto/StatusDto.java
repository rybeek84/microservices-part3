package com.ict.ms.microservices.task.api.rest.dto;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ict.ms.microservices.task.domain.Status;
import com.ict.ms.microservices.task.domain.exception.InvalidStatusException;

import lombok.Getter;

@Getter
public class StatusDto {
    String value;

    @JsonCreator
    StatusDto(){
    }

    public StatusDto(String value) {
        this.value = value;
    }

    public Status getAsEnum(){
        return Stream.of(Status.values())
                .filter(status -> status.name().equals(value))
                .findAny()
                .orElseThrow(() -> new InvalidStatusException(value));
    }
}
