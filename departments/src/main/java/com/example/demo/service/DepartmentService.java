package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Department> getDepartmentsStartingWith(String prefix) {
        return departmentRepository.findAllByNameStartingWith(prefix);
    }

    public List<Department> getDepartmentsEndingWith(String postfix) {
        return departmentRepository.findAllByNameEndingWith(postfix);
    }

    public Department addDepartment(Department department) {
        return departmentRepository.saveAndFlush(department);
    }

    public Department getDepartment(Long id) {
        randomSleep();
        return Optional.ofNullable(departmentRepository.findOne(id))
                .orElseThrow(DepartmentNotFoundException::new);
    }

    private void randomSleep() {
        try {
            Thread.sleep((long) (100 + (Math.random() * 500)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteDepartment(Long id) {
        departmentRepository.delete(getDepartment(id));
    }

    public void updateDepartment(Department department) {
        getDepartment(department.getId());
        departmentRepository.saveAndFlush(department);
    }

    @Transactional
    public void replaceNameWithId() {
        departmentRepository.replaceNameWithId();
    }
}
