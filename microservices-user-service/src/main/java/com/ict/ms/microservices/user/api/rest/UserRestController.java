package com.ict.ms.microservices.user.api.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.ms.microservices.user.domain.User;
import com.ict.ms.microservices.user.domain.UserDomainRepository;
import com.ict.ms.microservices.user.domain.vo.UserEmail;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class UserRestController {


	Logger logger = LoggerFactory.getLogger(UserRestController.class);

	private UserDomainRepository userRepository;

	@Autowired
	public UserRestController(UserDomainRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping(value = "/users")
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@GetMapping(value = "/users/{email}")
	public User findOne(@PathVariable("email") UserEmail userEmail) {

		return userRepository.findByEmail(userEmail).orElse(null);
	}

}
