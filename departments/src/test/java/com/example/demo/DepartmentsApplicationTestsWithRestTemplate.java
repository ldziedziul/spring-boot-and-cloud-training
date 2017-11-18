package com.example.demo;

import com.example.demo.common.Profiles;
import com.example.demo.dto.DepartmentDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(Profiles.DEV)
public class DepartmentsApplicationTestsWithRestTemplate {
    private static final Logger log = LoggerFactory.getLogger(DepartmentsApplicationTestsWithRestTemplate.class);
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int serverPort;

    @Before
    public void setUp() throws Exception {
        log.warn("Server is running on port {}", serverPort);
    }

    @Test
    public void shouldAddAndReadDepartment() throws Exception {
        //given
        DepartmentDto dto = new DepartmentDto();
        dto.setName("some-name");
        URI uri = restTemplate.postForLocation("/departments", dto);
        //when
        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(uri, DepartmentDto.class);
        //then
        assertThat(responseEntity.getBody().getName(), is("some-name"));
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }
}
