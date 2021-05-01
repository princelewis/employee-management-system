package com.activeedgetech.employeemanager.service;

import com.activeedgetech.employeemanager.dto.EmployeePayload;
import com.activeedgetech.employeemanager.dto.mapper.EmployeeMapper;
import com.activeedgetech.employeemanager.dto.response.AppResponse;
import com.activeedgetech.employeemanager.model.Employee;
import com.activeedgetech.employeemanager.repository.EmployeeRepository;
import com.activeedgetech.employeemanager.util.AppConstants;
import com.activeedgetech.employeemanager.util.UniqueIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service(value = "employeeService")
@Slf4j
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private UniqueIdGenerator uniqueIdGenerator;

    /**
     * Save an employee
     *
     * @param request the employee record to save
     * @return the saved employee record
     */
    @Override
    public AppResponse createEmployee(EmployeePayload request) {
        log.info("Creating a new employing record");

        request.setEmployeeId(uniqueIdGenerator.generateId());
        Employee employee = employeeMapper.toEntity(request);
        employee = employeeRepository.save(employee);
        EmployeePayload employeePayload = employeeMapper.toDto(employee);

        AppResponse response = new AppResponse();
        response.setMessage(AppConstants.EMPLOYEE_CREATION_SUCCESS_MESSAGE);
        response.setEmployees(employeePayload);

        return response;
    }

    @Override
    public AppResponse updateEmployee(EmployeePayload request) {
        return null;
    }
}
