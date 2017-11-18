package com.example.demo.common.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ExceptionDto {
    @NonNull
    private String message;
}
