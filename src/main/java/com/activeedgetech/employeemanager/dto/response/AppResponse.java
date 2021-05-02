package com.activeedgetech.employeemanager.dto.response;

import com.activeedgetech.employeemanager.dto.EmployeePayload;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Response DTO for the service
 */
@Data
public class AppResponse {

    private String message;
    private List<EmployeePayload> employees = new ArrayList<>();

    public void setEmployees(EmployeePayload employeePayload) {
        employees.add(employeePayload);
    }
}
