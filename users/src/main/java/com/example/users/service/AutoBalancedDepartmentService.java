package com.example.users.service;

import com.example.users.model.Department;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class AutoBalancedDepartmentService implements DepartmentsService {

    private static final String DEPARTMENTS_RESOURCE_URI = "http://departments/departments/";

    private RestTemplate restTemplate;

    public AutoBalancedDepartmentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "departments", unless="#result == null")
    @Override
    public Optional<Department> getDepartmentById(Long id) {
        return getDepartment(DEPARTMENTS_RESOURCE_URI + id);
    }

    private Optional<Department> getDepartment(String serviceUri) {
        try {
            return Optional.of(restTemplate.getForObject(serviceUri, Department.class));
        } catch (HttpClientErrorException ex) {
            return Optional.empty();
        }
    }

}
