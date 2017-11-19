package com.example.demo.actuator;

import org.springframework.boot.actuate.endpoint.SystemPublicMetrics;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.actuate.metrics.Metric;

public class MemoryIndicator implements HealthIndicator {
    private final SystemPublicMetrics systemPublicMetrics;

    public MemoryIndicator(SystemPublicMetrics systemPublicMetrics) {
        this.systemPublicMetrics = systemPublicMetrics;
    }

    @Override
    public Health health() {
        Long value = systemPublicMetrics.metrics().stream().filter(m -> m.getName().equals("mem.free"))
                .map(m -> (Metric<Long>) m)
                .map(Metric::getValue).findFirst().orElse(-1L);
        return Health.status(value > 500_000 ? Status.UP : Status.DOWN).withDetail("mem.free", value).build();
    }
}
