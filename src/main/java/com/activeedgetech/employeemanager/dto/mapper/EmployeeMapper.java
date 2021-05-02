package com.activeedgetech.employeemanager.dto.mapper;


import com.activeedgetech.employeemanager.dto.EmployeePayload;
import com.activeedgetech.employeemanager.model.Employee;

/**
 * Extend the capabilities of the EntityMapper for mapping
 * DTOs to Entities and from Entities to DTOs
 */
public interface EmployeeMapper extends  EntityMapper<EmployeePayload, Employee>{
}
