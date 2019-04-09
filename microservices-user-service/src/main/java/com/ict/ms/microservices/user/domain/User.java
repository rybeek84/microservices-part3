package com.ict.ms.microservices.user.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.ict.ms.microservices.user.domain.vo.UserEmail;

import lombok.Data;

@Entity
@Data
public class User {

    @EmbeddedId
    private UserEmail userId;
    private String firstName;
    private String lastName;
}
