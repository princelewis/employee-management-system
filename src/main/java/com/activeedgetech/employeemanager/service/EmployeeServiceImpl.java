package com.activeedgetech.employeemanager.service;

import com.activeedgetech.employeemanager.dto.EmployeePayload;
import com.activeedgetech.employeemanager.dto.mapper.EmployeeMapper;
import com.activeedgetech.employeemanager.dto.mapper.EmployeeMapperImpl;
import com.activeedgetech.employeemanager.dto.response.AppResponse;
import com.activeedgetech.employeemanager.exception.BadRequestException;
import com.activeedgetech.employeemanager.model.Employee;
import com.activeedgetech.employeemanager.repository.EmployeeRepository;
import com.activeedgetech.employeemanager.util.AppConstant;
import com.activeedgetech.employeemanager.util.UniqueIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public EmployeePayload createEmployee(EmployeePayload request) {
        log.info("Creating a new employing record");

        request.setEmployeeId(uniqueIdGenerator.generateId());
        Employee employee = employeeMapper.toEntity(request);
        employee = employeeRepository.save(employee);
        EmployeePayload employeePayload = employeeMapper.toDto(employee);

        //Create the response object
//        AppResponse response = new AppResponse();
//        response.setMessage(AppConstant.EMPLOYEE_CREATION_SUCCESS_MESSAGE);
//        response.setEmployees(List.of(employeePayload));

        return employeePayload;
    }

    /**
     * Update an employee record
     *
     * @param request the employee information to be updated with
     * @return the response object which has a response message and the saved employee record
     */
    @Override
    public EmployeePayload updateEmployee(EmployeePayload request) {

        log.info("Updating an already existing employee record");

        Employee employee = employeeMapper.toEntity(request);
        employee = employeeRepository.save(employee);
        EmployeePayload employeePayload = employeeMapper.toDto(employee);

        return employeePayload;
    }

    /**
     * Fetch all the stored employee data
     *
     * @return response object with response message and list of all the stored employee
     */
    @Override
    public AppResponse findAll() {
        log.info("Fetch all employee records from DB");

        List<EmployeePayload> fetchedData = employeeRepository.findAll()
                .stream().map(employeeMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));

        AppResponse response = new AppResponse();
        response.setEmployees(fetchedData);
        return response;
    }

    /**
     * Fetch one employee record
     *
     * @param id The id of the employee to fetch
     * @return response object with response message and the selected employee detail
     */
    @Override
    public EmployeePayload findOne(String id) {

        log.info("Fetch one employee record with id *** {}", id);
        Employee employee = employeeRepository.findByEmployeeId(id)
                .orElseThrow(() -> new BadRequestException("The Id sent is incorrect"));

        EmployeePayload employeePayload = employeeMapper.toDto(employee);

        return employeePayload;
    }

    /**
     * Delete the employee by id.
     *
     * @param id the id of the employee.
     */
    @Override
    public void delete(String id) {
        employeeRepository.deleteByEmployeeId(id);
    }
}
