package com.example.demo.common.config;

import com.example.demo.common.JsonConverter;
import com.example.demo.common.Mapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
