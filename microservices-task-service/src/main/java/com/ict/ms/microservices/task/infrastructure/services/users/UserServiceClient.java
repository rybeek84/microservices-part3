package com.ict.ms.microservices.task.infrastructure.services.users;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "users", url = "http://localhost:8180/api/v1/users")
public interface UserServiceClient {

	@RequestMapping(method = RequestMethod.GET, path = "/{email}")
	Optional<UserDto> findOne(@PathVariable("email") String userEmail);

}
