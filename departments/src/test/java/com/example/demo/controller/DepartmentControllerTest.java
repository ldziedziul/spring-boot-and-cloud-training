package com.example.demo.controller;

import com.example.demo.common.JsonConverter;
import com.example.demo.common.config.CommonBeans;
import com.example.demo.common.dto.ErrorEntry;
import com.example.demo.common.dto.ValidationErrorDto;
import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import org.junit.Before;
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
import static org.junit.Assert.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(DepartmentController.class)
@Import(CommonBeans.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private JsonConverter jsonConverter;

    @Before
    public void setUp() throws Exception {
        when(departmentService.addDepartment(any())).thenAnswer(invocation -> {
            Department department = invocation.getArgumentAt(0, Department.class);
            department.setId(1L);
            return department;
        });
    }

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
        mockMvc.perform(MockMvcRequestBuilders.post("/departments").content("{\"name\": \"some-name\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(departmentService).addDepartment(any());
    }

    @Test
    public void shouldNotAddDepartmentWithShortName() throws Exception {
        //when
        String response = mockMvc
                .perform(MockMvcRequestBuilders.post("/departments").content("{\"name\": \"ab\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();
        //then
        ValidationErrorDto validationErrorDto = jsonConverter.fromJson(response, ValidationErrorDto.class);
        assertThat(validationErrorDto.getErrors(), hasSize(1));
        ErrorEntry errorEntry = validationErrorDto.getErrors().get(0);
        assertErrorEntry(errorEntry, "departmentDto.name.Size", "size must be between 3 and 2147483647");
        verifyZeroInteractions(departmentService);
    }

    @Test
    public void shouldNotAddDepartmentWithoutName() throws Exception {
        //when
        String response = mockMvc
                .perform(MockMvcRequestBuilders.post("/departments").content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();
        //then
        ValidationErrorDto validationErrorDto = jsonConverter.fromJson(response, ValidationErrorDto.class);
        assertThat(validationErrorDto.getErrors(), hasSize(1));
        ErrorEntry errorEntry = validationErrorDto.getErrors().get(0);
        assertErrorEntry(errorEntry, "departmentDto.name.NotNull", "may not be null");
        verifyZeroInteractions(departmentService);
    }

    private void assertErrorEntry(ErrorEntry errorEntry, String code, String description) {
        assertThat(errorEntry.getObject(), is("departmentDto"));
        assertThat(errorEntry.getCode(), is(code));
        assertThat(errorEntry.getField(), is("name"));
        assertThat(errorEntry.getDescription(), is(description));
    }
}