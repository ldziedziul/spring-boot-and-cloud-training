package com.example.demo.controller;

import com.example.demo.common.config.Beans;
import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(DepartmentController.class)
@Import(Beans.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @Test
    public void shouldList() throws Exception {
        when(departmentService.getDepartments()).thenReturn(Arrays.asList(new Department(1L, "dep 1"),new Department(2L, "dep 2")));
        mockMvc.perform(MockMvcRequestBuilders.get("/departments"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("dep 1")))
                .andExpect(jsonPath("$[1].name", is("dep 2")));

    }

    @Test
    public void shouldAdd() throws Exception {
        when(departmentService.addDepartment(any())).thenAnswer(invocation -> {
            Department department = invocation.getArgumentAt(0, Department.class);
            department.setId(1L);
            return department;
        });

        mockMvc.perform(MockMvcRequestBuilders.post("/departments").content("{\"name\": \"some-name\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}