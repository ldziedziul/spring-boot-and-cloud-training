package com.example.users.service;

import com.example.users.model.Department;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "departments", decode404 = true)
public interface FeignDepartmentsClient {

    @RequestMapping(method = RequestMethod.GET, value = "departments/{id}")
    Department getDepartmentById(@PathVariable("id") Long id);

}
