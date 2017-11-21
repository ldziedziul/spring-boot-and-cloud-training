package com.example.users.config;

import com.example.demo.common.web.UriBuilder;
import com.example.users.model.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String USERS_URL = "/users";
    private static final String ACTIVE_USER_URL = USERS_URL + "/active";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, USERS_URL).permitAll()
                .antMatchers(HttpMethod.GET, ACTIVE_USER_URL).authenticated()
                .antMatchers(UriBuilder.PUBLIC_URIS).permitAll()
                .antMatchers(UriBuilder.ALL_URIS).hasAnyRole(Role.ADMIN.name());
    }

}
