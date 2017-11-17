package com.example.demo.controller;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/departments")
@Api(description = "Departments resource")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("List all departments")
    public List<DepartmentDto> list() {
        return departmentService.getDepartments().stream().map(this::toDto).collect(Collectors.toList());
    }

    private DepartmentDto toDto(Department department) {
        DepartmentDto dto = new DepartmentDto();
        dto.setName(department.getName());
        return dto;
    }

}
