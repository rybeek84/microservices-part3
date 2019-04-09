package com.ict.ms.microservices.user.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.ms.microservices.user.domain.vo.UserEmail;
import com.ict.ms.microservices.user.infrastructure.persistence.UserJpaRepository;

@Service
public class UserDomainRepository {

	private UserJpaRepository userJpaRepository;

	@Autowired
	public UserDomainRepository(UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}

	public List<User> findAll() {
		return userJpaRepository.findAll();
	}

	public Optional<User> findByEmail(UserEmail userEmail) {
		return userJpaRepository.findById(userEmail);
	}

}
