package com.activeedgetech.employeemanager.dto.response;

import com.activeedgetech.employeemanager.dto.EmployeePayload;
import lombok.Data;

import java.util.List;

/**
 * Response DTO for the service
 */
@Data
public class AppResponse {

    private List<EmployeePayload> employees;

}
