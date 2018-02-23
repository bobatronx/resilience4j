/*
 * Copyright 2017 Robert Winkler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.resilience4j.circuitbreaker.monitoring.endpoint;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;


/**
 * {@link Endpoint} to expose CircuitBreaker events.
 */
@ConfigurationProperties(prefix = "endpoints.circuitbreaker")
@Endpoint(id = "circuitbreaker")
public class CircuitBreakerEndpoint {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public CircuitBreakerEndpoint(CircuitBreakerRegistry circuitBreakerRegistry) {
        this.circuitBreakerRegistry = circuitBreakerRegistry;
    }

    @ReadOperation
    public List<String> getCircuitBreakerInfo() {
        return circuitBreakerRegistry
                .getAllCircuitBreakers()
                .map(CircuitBreaker::getName)
                .sorted()
                .toJavaList();
    }
}
