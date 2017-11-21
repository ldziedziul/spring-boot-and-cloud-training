package com.example.users.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "users")
@Entity
@Data
public class User  {

    @GeneratedValue
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private Long departmentId;

}
