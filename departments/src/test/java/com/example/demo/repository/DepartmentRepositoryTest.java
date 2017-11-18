package com.example.demo.repository;

import com.example.demo.model.Department;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


@DataJpaTest
@RunWith(SpringRunner.class)
public class DepartmentRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private DepartmentRepository sut;

    @Before
    public void setUp() throws Exception {
        em.persist(createDepartment("a dep 1"));
        em.persist(createDepartment("a dep 2"));
        em.persist(createDepartment("b dep 2"));
        em.persist(createDepartment("c dep 3"));
        em.persist(createDepartment("d dep 3"));
        em.flush();
        em.clear();
    }

    @Test
    public void shouldFindAllByNameStartingWith() throws Exception {
        //when
        List<Department> departments = sut.findAllByNameStartingWith("a");
        //then
        assertThat(departments, hasSize(2));
        assertThat(names(departments), hasItems("a dep 1", "a dep 2"));
    }

    private List<String> names(List<Department> departments) {
        return departments.stream().map(Department::getName).collect(Collectors.toList());
    }

    private Department createDepartment(String name) {
        Department department = new Department();
        department.setName(name);
        return department;
    }

    @Test
    public void shouldFindAllByNameEndingWith() throws Exception {
        //given
        //when
        List<Department> departments = sut.findAllByNameEndingWith("3");
        //then
        assertThat(departments, hasSize(2));
        assertThat(names(departments), hasItems("c dep 3", "d dep 3"));
    }

    @Test
    public void shouldReplaceNameWithId() throws Exception {
        //when
        sut.replaceNameWithId();
        //then
        List<Department> changedDepartments = sut.findAll();
        assertThat(changedDepartments, hasSize(5));
        assertThat(names(changedDepartments), equalTo(ids(changedDepartments)));
    }

    private List<String> ids(List<Department> departments) {
        return departments.stream()
                .map(Department::getId)
                .map(Object::toString)
                .collect(Collectors.toList());
    }

}