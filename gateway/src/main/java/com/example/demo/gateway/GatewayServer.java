package com.example.demo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class GatewayServer {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServer.class);
    }
    @Bean
    public SampleZuulFilter sampleZuulFilter() {
        return new SampleZuulFilter();
    }
}
