package com.example.demo.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorEntry {
    private String object;
    private String field;
    private String code;
    private String description;
}
