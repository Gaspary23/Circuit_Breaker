package com.engsoft2.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@SpringBootApplication
public class ApiGatewayApplication {

	@Autowired
	private CircuitBreaker circuitBreaker;

	@RequestMapping("/currency-conversion")
	public Mono<String> readingList() {
		return circuitBreaker.readingList();
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
