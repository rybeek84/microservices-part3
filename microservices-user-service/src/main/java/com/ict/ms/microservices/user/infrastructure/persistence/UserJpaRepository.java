package com.ict.ms.microservices.user.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ict.ms.microservices.user.domain.User;
import com.ict.ms.microservices.user.domain.vo.UserEmail;

public interface UserJpaRepository extends JpaRepository<User, UserEmail> {
    List<User> findByUserId(UserEmail userId);
}
