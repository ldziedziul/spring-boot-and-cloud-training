package com.example.demo.repository;

import com.example.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findAllByNameStartingWith(String prefix);

    @Query("select e from #{#entityName} e where e.name like %?1")
    List<Department> findAllByNameEndingWith(String postfix);

    @Modifying
    @Query("update Department d set d.name = d.id")
    int replaceNameWithId();
}
