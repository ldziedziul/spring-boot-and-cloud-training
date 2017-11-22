package com.example.users.service;

import com.example.users.model.Department;

import java.util.Optional;

public interface DepartmentsService {
	Optional<Department> getDepartmentById(Long departmentId);
}
