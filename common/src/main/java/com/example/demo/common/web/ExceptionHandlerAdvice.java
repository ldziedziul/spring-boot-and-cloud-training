package com.example.demo.common.web;

import com.example.demo.common.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
    private final Mapper mapper;

    public ExceptionHandlerAdvice(Mapper mapper) {
        this.mapper = mapper;
    }

    @ExceptionHandler
    public ResponseEntity handleException(Exception ex, Locale locale) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(mapper.map(ex, locale), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
