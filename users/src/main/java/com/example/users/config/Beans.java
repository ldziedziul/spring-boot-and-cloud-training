package com.example.users.config;

import com.example.demo.common.config.CommonBeans;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = CommonBeans.class)
public class Beans {

}
