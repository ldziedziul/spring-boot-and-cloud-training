package com.example.demo.config;

import com.example.demo.actuator.MemoryIndicator;
import org.springframework.boot.actuate.endpoint.SystemPublicMetrics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    MemoryIndicator memoryIndicator(SystemPublicMetrics systemPublicMetrics) {
        return new MemoryIndicator(systemPublicMetrics);
    }
}
