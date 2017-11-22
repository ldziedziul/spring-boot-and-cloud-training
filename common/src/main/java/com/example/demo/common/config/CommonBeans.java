package com.example.demo.common.config;

import com.example.demo.common.JsonConverter;
import com.example.demo.common.Mapper;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
@EnableFeignClients("com.example")
@EnableCircuitBreaker
public class CommonBeans {

    @Bean
    public Mapper mapper(MessageSource messageSource) {
        return new Mapper(messageSource);
    }

    @Bean
    public JsonConverter jsonConverter(){
        return new JsonConverter();
    }
}
