package com.example.demo.common;

import com.example.demo.common.web.ExceptionDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Mapper {
    private final MessageSource messageSource;

    public Mapper(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public static <TO, FROM> TO map(FROM src, Class<TO> dest) {
        return new ModelMapper().map(src, dest);
    }

    public static <TO, FROM> TO map(FROM src, TO dest) {
        new ModelMapper().map(src, dest);
        return dest;
    }

    public static <TO, FROM> List<TO> map(List<FROM> src, Class<TO> dest) {
        return src.stream().map(s -> map(s, dest)).collect(Collectors.toList());
    }

    public ExceptionDto map(Exception ex, Locale locale) {
        String className = ex.getClass().getSimpleName();
        String message;
        try {
            message = messageSource.getMessage(className, null, locale);
        } catch (NoSuchMessageException msgEx) {
            message = className;
        }

        return new ExceptionDto(message);
    }
}
