package com.example.users.service;

import com.example.users.model.Department;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeignDepartmentsService implements DepartmentsService {

    private final FeignDepartmentsClient feignDepartmentsClient;

    public FeignDepartmentsService(FeignDepartmentsClient feignDepartmentsClient) {
        this.feignDepartmentsClient = feignDepartmentsClient;
    }

    @Override
    public Optional<Department> getDepartmentById(Long id) {
        Department department = feignDepartmentsClient.getDepartmentById(id);
        return department != null ? Optional.of(department) : Optional.empty();
    }

}
