package com.engsoft2.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class CircuitBreakerGatewayFilterFactory {
    private static final Logger LOG = LoggerFactory.getLogger(CircuitBreakerGatewayFilterFactory.class);

    private final WebClient webClient;
    private final ReactiveCircuitBreaker CircuitBreaker;

    public CircuitBreakerGatewayFilterFactory(ReactiveCircuitBreakerFactory circuitBreakerFactory) {
    this.webClient = WebClient.builder().baseUrl("http://localhost:8765").build();
    this.CircuitBreaker = circuitBreakerFactory.create("recommended");
    }

    public Mono<String> readingList() {
        return CircuitBreaker.run(webClient.get().uri("/recommended").retrieve().bodyToMono(String.class),
                throwable -> {
                    LOG.warn("Error making request to conversion service", throwable);
                    return Mono.just("Cloud Native Java (O'Reilly)");
                });
    }
}
