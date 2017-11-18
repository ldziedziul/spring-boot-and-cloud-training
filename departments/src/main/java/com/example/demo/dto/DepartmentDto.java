package com.example.demo.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("department")
@Data
public class DepartmentDto {

    private String name;

}
