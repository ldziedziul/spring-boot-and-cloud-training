package com.example.demo.common.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorDto {
    private List<ErrorEntry> errors = new ArrayList<>();

    public void addError(String object, String field, String code, String description) {
        errors.add(new ErrorEntry(object, field, code, description));
    }
}

