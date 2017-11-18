package com.example.demo.service;

import com.example.demo.model.Department;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DepartmentService {

    public List<Department> getDepartments() {
        return Arrays.asList(new Department("department 1", 1L), new Department("department 2", 2L));
    }
}
