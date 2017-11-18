package com.example.demo.controller;

import com.example.demo.common.Mapper;
import com.example.demo.common.web.UriBuilder;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/departments")
@Api(description = "Departments resource")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final Mapper mapper;

    public DepartmentController(DepartmentService departmentService, Mapper mapper) {
        this.departmentService = departmentService;
        this.mapper = mapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("List all departments")
    public List<DepartmentDto> list() {
        return mapper.map(departmentService.getDepartments(), DepartmentDto.class);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation("Add new department")
    public ResponseEntity add(@RequestBody DepartmentDto dto) {
        Department department = departmentService.addDepartment(mapper.map(dto, Department.class));
        return created(UriBuilder.requestUriWithId(department.getId())).build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    @ApiOperation("Get departments")
    public DepartmentDto getDepartment(@PathVariable("id") Long id) {
        return mapper.map(departmentService.getDepartment(id), DepartmentDto.class);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    @ApiOperation("Get departments")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
        return noContent().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    @ApiOperation("Update department")
    public ResponseEntity updateDepartment(@RequestBody DepartmentDto dto, @PathVariable("id") Long id) {
        Department department = mapper.map(dto, Department.class);
        department.setId(id);
        departmentService.updateDepartment(department);
        return noContent().build();
    }


}
