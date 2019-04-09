package com.ict.ms.microservices.user.infrastructure.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.ict.ms.microservices.user.infrastructure.persistence")
@EntityScan(basePackages = "com.ict.ms.microservices.user.domain")
@EnableTransactionManagement
public class JpaConfiguration {
}
