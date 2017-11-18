package com.example.demo;

import com.example.demo.common.JsonConverter;
import com.example.demo.dto.DepartmentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentsApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonConverter jsonConverter;

    @Test
    public void contextLoads() throws Exception {
        mockMvc.perform(get("/index.html"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Wprowadzenie do spring io"));
    }

    @Test
    public void shouldAddAndReadDepartment() throws Exception {
        //given
        DepartmentDto dto = new DepartmentDto();
        dto.setName("some-name");
        String location = mockMvc.perform(post("/departments").content(jsonConverter.toJson(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getHeader("location");
        //when
        String response = mockMvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("some-name")))
                .andReturn().getResponse().getContentAsString();
        //then

        DepartmentDto readDepartment = jsonConverter.fromJson(response, DepartmentDto.class);
        assertThat(readDepartment.getName(), is("some-name"));
    }


}
