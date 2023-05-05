package com.engsoft2.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/currency-exchange/**")
						.filters(f -> f.circuitBreaker(
								c -> c.setName("circuitbreaker")
										.setFallbackUri("forward:/error-handler")))
						.uri("lb://currency-exchange"))
				.route(p -> p.path("/currency-conversion/**")
						.filters(f -> f.circuitBreaker(
								c -> c.setName("circuitbreaker")
										.setFallbackUri("forward:/error-handler")))
						.uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-conversion-feign/**").filters(f -> f
						.circuitBreaker(c -> c.setName("circuitbreaker")
								.setFallbackUri("forward:/error-handler")))
						.uri("lb://currency-conversion"))
				.build();
	}
}
