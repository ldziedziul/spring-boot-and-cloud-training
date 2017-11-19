package com.example.demo.common.config;

import com.example.demo.common.JsonConverter;
import com.example.demo.common.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CommonBeans {
    private static final Logger log = LoggerFactory.getLogger(CommonBeans.class);

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Mapper mapper(MessageSource messageSource) {
        log.error("Creating new mapper");
        return new Mapper(messageSource);
    }

    @Bean
    public JsonConverter jsonConverter(){
        return new JsonConverter();
    }
}
