package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Department addDepartment(Department department) {
        return departmentRepository.saveAndFlush(department);
    }

    public Department getDepartment(Long id) {
        return Optional.ofNullable(departmentRepository.findOne(id))
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.delete(getDepartment(id));
    }

    public void updateDepartment(Department department) {
        getDepartment(department.getId());
        departmentRepository.saveAndFlush(department);
    }
}
