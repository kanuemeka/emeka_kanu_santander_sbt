package com.kanue.interviews.santander.accountrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.kanue.interviews.santander.accountrestapi")
@EnableJpaRepositories("com.kanue.interviews.santander.accountrestapi.repository")
public class AccountRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountRestApiApplication.class, args);
	}

}
