package com.activeedgetech.employeemanager.service;

import com.activeedgetech.employeemanager.dto.EmployeePayload;
import com.activeedgetech.employeemanager.dto.response.AppResponse;

public interface EmployeeService {
    EmployeePayload createEmployee(EmployeePayload request);
    EmployeePayload updateEmployee(EmployeePayload request);
    AppResponse findAll();
    EmployeePayload findOne(String id);
    void delete(String id);
}
