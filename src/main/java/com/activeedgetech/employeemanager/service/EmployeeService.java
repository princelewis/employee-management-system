package com.activeedgetech.employeemanager.service;

import com.activeedgetech.employeemanager.dto.EmployeePayload;
import com.activeedgetech.employeemanager.dto.response.AppResponse;

public interface EmployeeService {
    AppResponse createEmployee(EmployeePayload request);
    AppResponse updateEmployee(EmployeePayload request);
}
