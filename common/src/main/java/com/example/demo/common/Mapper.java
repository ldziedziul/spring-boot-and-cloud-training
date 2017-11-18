package com.example.demo.common;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public final class Mapper {
    private Mapper() {
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
}
