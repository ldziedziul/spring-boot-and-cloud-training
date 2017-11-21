package com.example.demo.common.web;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class UriBuilder {

    public static final String[] PUBLIC_URIS = {
            "/",
            "/configuration/security",
            "/configuration/ui",
            "/swagger-resources",
            "/swagger-resources/configuration/ui",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
    };

    public static final String ALL_URIS = "/**";

    public static URI requestUriWithId(long id) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
