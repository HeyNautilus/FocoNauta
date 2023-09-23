package com.projeto.senac;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.ComponentScan;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;


@EntityScan(basePackages = "com.projeto.senac. ")
@ComponentScan(basePackages = "com.projeto.senac.*")
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.projeto.senac.repository")
@EnableTransactionManagement
@RestController
@EnableAutoConfiguration
public class ProjetosenacApplication {


	public static void main(String[] args) {
		SpringApplication.run(ProjetosenacApplication.class, args);
	}
	

}
