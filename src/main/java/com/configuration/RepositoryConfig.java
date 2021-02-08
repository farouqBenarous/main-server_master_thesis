package com.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration("application.properties")
@EnableJpaRepositories(basePackages = "com.database")
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.database.table"})
public class RepositoryConfig {
}
