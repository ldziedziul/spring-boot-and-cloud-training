package com.example.demo.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel("department")
@Data
public class DepartmentDto {
    @Size(min = 3)
    @NotNull
    private String name;

}
