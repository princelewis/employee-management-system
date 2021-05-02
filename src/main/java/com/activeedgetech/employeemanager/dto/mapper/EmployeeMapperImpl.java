package com.activeedgetech.employeemanager.dto.mapper;

import com.activeedgetech.employeemanager.dto.EmployeePayload;
import com.activeedgetech.employeemanager.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class EmployeeMapperImpl implements EmployeeMapper{
    @Override
    public Employee toEntity(EmployeePayload dto) {
        if (dto == null) {
            return null;
        }

        log.info("Mapping DTOs ** {} to entities", dto.toString());

        Employee employee = new Employee();
        employee.setEmployeeId(dto.getEmployeeId());
        employee.setAge(dto.getAge());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setJoinDate(dto.getJoinDate());
        return  employee;
    }

    @Override
    public EmployeePayload toDto(Employee entity) {
        if (entity == null) {
            return null;
        }

        log.info("Mapping Entity ** {} to DTO", entity.toString());

        EmployeePayload employeePayload = new EmployeePayload();
        employeePayload.setAge(entity.getAge());
        employeePayload.setEmployeeId(entity.getEmployeeId());
        employeePayload.setFirstName(entity.getFirstName());
        employeePayload.setLastName(entity.getLastName());
        employeePayload.setJoinDate(entity.getJoinDate());
        return employeePayload;
    }

    @Override
    public List<Employee> toEntity(List<EmployeePayload> dtoList) {
        if (dtoList == null) {
            return null;
        }
        List<Employee> list = new ArrayList<>();

        for(EmployeePayload employeePayload : dtoList) {
            list.add(toEntity(employeePayload));
        }

        return list;
    }

    @Override
    public List<EmployeePayload> toDto(List<Employee> entityList) {
        if (entityList == null) {
            return null;
        }

        List<EmployeePayload> list = new ArrayList<>();

        for (Employee employee : entityList) {
            list.add(toDto(employee));
        }
        return list;
    }

}
