package com.activeedgetech.employeemanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class EmployeePayload implements Serializable {

    @JsonProperty("employee_id")
    private String employeeId;

    @JsonProperty("first_name")
    @NotBlank(message = "first name cannot be blank")
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank(message = "last name cannot be blank")
    private String lastName;

    @NotBlank(message = "age is mandatory")
    private String age;

    @JsonProperty("join_date")
    @NotNull(message = "join date is mandatory")
    private LocalDate joinDate;

}
