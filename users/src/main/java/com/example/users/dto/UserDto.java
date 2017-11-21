package com.example.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonProperty.Access;

@ApiModel(value = "User")
@Data
public class UserDto {

    private String firstName;
    private String lastName;
    @JsonProperty(access = Access.READ_ONLY)
    private String departmentName;
    @JsonProperty(access = Access.WRITE_ONLY)
    private Long departmentId;
}
