package com.activeedgetech.employeemanager.model;

import com.activeedgetech.employeemanager.model.audit.DateAudit;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


/**
 * Entity for persisting employee data in the database
 */
@Entity
@Data
public class Employee extends DateAudit {

    @Id
    @Column(name = "employee_id", nullable = false)
    @NaturalId
    @NotBlank
    private String employeeId;

    @Column(name = "first_name", nullable = false)
    @NotBlank
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    private String lastName;

    @NotBlank
    private String age;

    @Column(name = "join_date", nullable = false)
    @NotNull
    private LocalDate joinDate;

}
