package com.example.demo.dto;


import io.swagger.annotations.ApiModel;

@ApiModel("department")
public class DepartmentDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
