package com.activeedgetech.employeemanager.service;

import com.activeedgetech.employeemanager.dto.EmployeePayload;
import com.activeedgetech.employeemanager.dto.mapper.EmployeeMapper;
import com.activeedgetech.employeemanager.dto.response.AppResponse;
import com.activeedgetech.employeemanager.model.Employee;
import com.activeedgetech.employeemanager.repository.EmployeeRepository;
import com.activeedgetech.employeemanager.util.AppConstant;
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
     * @return the response object which has a response message and the saved employee record
     */
    @Override
    public AppResponse createEmployee(EmployeePayload request) {
        log.info("Creating a new employing record");

        request.setEmployeeId(uniqueIdGenerator.generateId());
        Employee employee = employeeMapper.toEntity(request);
        employee = employeeRepository.save(employee);
        EmployeePayload employeePayload = employeeMapper.toDto(employee);

        //Create the response object
        AppResponse response = new AppResponse();
        response.setMessage(AppConstant.EMPLOYEE_CREATION_SUCCESS_MESSAGE);
        response.setEmployees(employeePayload);

        return response;
    }

    /**
     * Update an employee record
     *
     * @param request the employee information to be updated with
     * @return the response object which has a response message and the saved employee record
     */
    @Override
    public AppResponse updateEmployee(EmployeePayload request) {

        log.info("Updating an already existing employee record");

        Employee employee = employeeMapper.toEntity(request);
        employee = employeeRepository.save(employee);
        EmployeePayload employeePayload = employeeMapper.toDto(employee);

        AppResponse response = new AppResponse();
        response.setMessage(AppConstant.EMPLOYEE_UPDATE_SUCCESS_MESSAGE);
        response.setEmployees(employeePayload);
        return response;
    }
}
