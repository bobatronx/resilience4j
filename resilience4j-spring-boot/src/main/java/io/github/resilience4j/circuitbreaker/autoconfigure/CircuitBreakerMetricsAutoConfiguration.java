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
package io.github.resilience4j.circuitbreaker.autoconfigure;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.dropwizard.DropwizardConfig;
import io.micrometer.core.instrument.dropwizard.DropwizardMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@link org.springframework.boot.autoconfigure.EnableAutoConfiguration
 * Auto-configuration} for resilience4j-metrics.
 */
//@Configuration
//@ConditionalOnClass(MeterRegistry.class)
//@AutoConfigureAfter(value = {CircuitBreakerAutoConfiguration.class, MetricsAutoConfiguration.class})
//TODO - Implement when micrometer is merged
public class CircuitBreakerMetricsAutoConfiguration {
//    @Bean
//    public CircuitBreakerMetrics registerCircuitBreakerMetrics(CircuitBreakerRegistry circuitBreakerRegistry, MetricRegistry metricRegistry) {
//        CircuitBreakerMetrics circuitBreakerMetrics = CircuitBreakerMetrics.ofCircuitBreakerRegistry(circuitBreakerRegistry);
//        metricRegistry.registerAll(circuitBreakerMetrics);
//        return circuitBreakerMetrics;
//    }
//
//    @Bean
//    DropwizardMeterRegistry dropwizardMeterRegistry(MetricRegistry metricRegistry) {
//        return new DropwizardMeterRegistry(null, metricRegistry, null, null);
//    }
}
