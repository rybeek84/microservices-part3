package com.ict.ms.microservices.task.domain.vo;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Embeddable
@Getter
@EqualsAndHashCode(of = "email")
public class UserEmail implements Serializable {

	private String email;

	UserEmail() {
	}

	public UserEmail(String email) {
		this.email = email.toLowerCase();
	}

}
