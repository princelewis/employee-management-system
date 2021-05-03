package com.activeedgetech.employeemanager.service;

import com.activeedgetech.employeemanager.dto.EmployeePayload;
import com.activeedgetech.employeemanager.dto.mapper.EmployeeMapper;
import com.activeedgetech.employeemanager.dto.response.AppResponse;
import com.activeedgetech.employeemanager.exception.BadRequestException;
import com.activeedgetech.employeemanager.model.Employee;
import com.activeedgetech.employeemanager.repository.EmployeeRepository;
import com.activeedgetech.employeemanager.util.UniqueIdGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @Mock
    private UniqueIdGenerator uniqueIdGenerator;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    Employee employee = new Employee();
    EmployeePayload employeePayload = new EmployeePayload();
    EmployeePayload generatedEmployeePayload = new EmployeePayload();

    @BeforeEach
    void init(){

        MockitoAnnotations.initMocks(this);

        employeePayload.setLastName("Ben");
        employeePayload.setFirstName("Ugwu");
        employeePayload.setAge("28");
        employeePayload.setJoinDate(LocalDate.parse("2021-10-22"));

        generatedEmployeePayload.setEmployeeId("E00002");
        generatedEmployeePayload.setLastName("Ben");
        generatedEmployeePayload.setFirstName("Ugwu");
        generatedEmployeePayload.setAge("28");
        generatedEmployeePayload.setJoinDate(LocalDate.parse("2021-10-22"));

        employee.setEmployeeId("E00002");
        employee.setLastName("Ben");
        employee.setFirstName("Ugwu");
        employee.setAge("28");
        employee.setJoinDate(LocalDate.parse("2021-10-22"));

        when(uniqueIdGenerator.generateId()).thenReturn("E00002");
        when(employeeMapper.toEntity(generatedEmployeePayload)).thenReturn(employee);
        when(employeeMapper.toDto(employee)).thenReturn(generatedEmployeePayload);
        when(employeeRepository.save(employee)).thenReturn(employee);
    }

    @Test
    void createEmployeeTest() {

        Assertions.assertEquals(generatedEmployeePayload, employeeService.createEmployee(employeePayload));

    }

    @Test
    void updateEmployeeTest(){
        Assertions.assertEquals(generatedEmployeePayload, employeeService.updateEmployee(generatedEmployeePayload));
    }

    @Test
    void findAllTest() {
        when(employeeRepository.findAll()).thenReturn(List.of(employee));

        AppResponse response = new AppResponse();
        response.setEmployees(List.of(generatedEmployeePayload));
        Assertions.assertEquals(response, employeeService.findAll());
    }

    @Test
    void findOne() {

        when(employeeRepository.findByEmployeeId("E00002")).thenReturn(Optional.ofNullable(employee));

        Assertions.assertEquals(generatedEmployeePayload, employeeService.findOne("E00002"));
    }

    @Test
    void findOneException() {

        when(employeeRepository.findByEmployeeId("E0000")).thenThrow(new BadRequestException("Wrong employee id"));

        Assertions.assertThrows(BadRequestException.class, () -> employeeService.findOne("E0000"));
    }

    @Test
    void delete() {

        doNothing().when(employeeRepository).deleteByEmployeeId("E00002");
        employeeService.delete("E00002");
        verify(employeeRepository, times(1)).deleteByEmployeeId("E00002");
    }
}
