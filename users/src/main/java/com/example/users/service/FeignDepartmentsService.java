package com.example.users.service;

import com.example.users.model.Department;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeignDepartmentsService implements DepartmentsService {

    private final FeignDepartmentsClient feignDepartmentsClient;

    public FeignDepartmentsService(FeignDepartmentsClient feignDepartmentsClient) {
        this.feignDepartmentsClient = feignDepartmentsClient;
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallbackDepartment")
    public Optional<Department> getDepartmentById(Long id) {
        Department department = feignDepartmentsClient.getDepartmentById(id);
        return department != null ? Optional.of(department) : Optional.empty();
    }

    public Optional<Department> fallbackDepartment(Long id) {
        Department department = new Department();
        department.setName("FALLBACK NAME");
        return Optional.of(department);
    }

}
