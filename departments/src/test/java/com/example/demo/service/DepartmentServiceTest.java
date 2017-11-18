package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;

public class DepartmentServiceTest {
    private DepartmentRepository departmentRepository = Mockito.mock(DepartmentRepository.class);
    private DepartmentService sut = new DepartmentService(departmentRepository);

    @Test
    public void shouldGetDepartments() throws Exception {
        //given
        when(departmentRepository.findAll()).thenReturn(Arrays.asList(new Department(), new Department()));
        //when
        List<Department> departments = sut.getDepartments();
        //then
        assertThat(departments, hasSize(2));
        verify(departmentRepository).findAll();
    }

    @Test
    public void shouldAddDepartment() throws Exception {
        //given
        when(departmentRepository.saveAndFlush(any())).thenAnswer(AdditionalAnswers.returnsFirstArg());
        //when
        Department department = new Department();
        Department result = sut.addDepartment(department);
        //then
        assertThat(result, is(sameInstance(department)));
        verify(departmentRepository).saveAndFlush(department);

    }
}