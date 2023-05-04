package com.engsoft2.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CircuitBreaker {
    private static final Logger LOG = LoggerFactory.getLogger(CircuitBreaker.class);

    private final WebClient webClient;
    private final ReactiveCircuitBreaker circuitBreaker;

    public CircuitBreaker(ReactiveCircuitBreakerFactory circuitBreakerFactory) {
        this.webClient = WebClient.builder().baseUrl("http://localhost:8765").build();
        this.circuitBreaker = circuitBreakerFactory.create("currency-conversion");
    }

    public Mono<String> readingList() {
        return circuitBreaker.run(webClient.get().uri("/currency-conversion").retrieve().bodyToMono(String.class),
                throwable -> {
                    LOG.warn("Error making request to book service", throwable);
                    return Mono.just("Cloud Native Java (O'Reilly)");
                });
    }
}
