/*
 * Copyright 2017 Jan Sykora
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
package io.github.resilience4j.metrics;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricSet;
import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.vavr.collection.Array;

import java.util.Map;

import static com.codahale.metrics.MetricRegistry.name;
import static java.util.Objects.requireNonNull;

/**
 * An adapter which exports {@link Bulkhead.Metrics} as Dropwizard Metrics Gauges.
 */
public class BulkheadMetrics implements MetricSet {
    private static final String DEFAULT_PREFIX = "resilience4j.bulkhead";
    private static final String AVAILABLE_CONCURRENT_CALLS = "available_concurrent_calls";

    private final MetricRegistry metricRegistry = new MetricRegistry();

    private BulkheadMetrics(Iterable<Bulkhead> bulkheads) {
        this(DEFAULT_PREFIX, bulkheads);
    }

    private BulkheadMetrics(String prefix, Iterable<Bulkhead> bulkheads) {
        requireNonNull(prefix);
        requireNonNull(bulkheads);
        bulkheads.forEach(bulkhead -> {
                    String name = bulkhead.getName();
            //number of available concurrent calls as an integer
                    metricRegistry.register(name(prefix, name, AVAILABLE_CONCURRENT_CALLS),
                            (Gauge<Integer>) () -> bulkhead.getMetrics().getAvailableConcurrentCalls());
                }
        );
    }

    /**
     * Creates a new instance BulkheadMetrics {@link BulkheadMetrics} with specified metrics names prefix and
     * a {@link BulkheadRegistry} as a source.
     *
     * @param prefix           the prefix of metrics names
     * @param bulkheadRegistry the registry of bulkheads
     */
    public static BulkheadMetrics ofBulkheadRegistry(String prefix, BulkheadRegistry bulkheadRegistry) {
        return new BulkheadMetrics(prefix, bulkheadRegistry.getAllBulkheads());
    }

    /**
     * Creates a new instance BulkheadMetrics {@link BulkheadMetrics} with
     * a {@link BulkheadRegistry} as a source.
     *
     * @param bulkheadRegistry the registry of bulkheads
     */
    public static BulkheadMetrics ofBulkheadRegistry(BulkheadRegistry bulkheadRegistry) {
        return new BulkheadMetrics(bulkheadRegistry.getAllBulkheads());
    }

    /**
     * Creates a new instance BulkheadMetrics {@link BulkheadMetrics} with
     * an {@link Iterable} of bulkheads as a source.
     *
     * @param bulkheads the bulkheads
     */
    public static BulkheadMetrics ofIterable(Iterable<Bulkhead> bulkheads) {
        return new BulkheadMetrics(bulkheads);
    }

    /**
     * Creates a new instance BulkheadMetrics {@link BulkheadMetrics} with
     * an {@link Iterable} of bulkheads as a source.
     *
     * @param bulkheads the bulkheads
     */
    public static BulkheadMetrics ofIterable(String prefix, Iterable<Bulkhead> bulkheads) {
        return new BulkheadMetrics(prefix, bulkheads);
    }


    /**
     * Creates a new instance of BulkheadMetrics {@link BulkheadMetrics} with a bulkhead as a source.
     *
     * @param bulkhead the circuit breaker
     */
    public static BulkheadMetrics ofBulkhead(Bulkhead bulkhead) {
        return new BulkheadMetrics(Array.of(bulkhead));
    }

    @Override
    public Map<String, Metric> getMetrics() {
        return metricRegistry.getMetrics();
    }
}
