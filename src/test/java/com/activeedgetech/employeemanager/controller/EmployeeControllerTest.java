package com.activeedgetech.employeemanager.controller;

import com.activeedgetech.employeemanager.dto.EmployeePayload;
import com.activeedgetech.employeemanager.dto.response.AppResponse;
import com.activeedgetech.employeemanager.model.Employee;
import com.activeedgetech.employeemanager.service.EmployeeService;
import com.activeedgetech.employeemanager.util.TestUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    private EmployeePayload employeePayload = new EmployeePayload();
    private EmployeePayload generatedEmployeePayload = new EmployeePayload();
    private Employee employee = new Employee();

    @BeforeEach
    public void init() {
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
    }

    @Test
    public void createEmployeeTest() throws Exception {
        when(employeeService.createEmployee(employeePayload)).thenReturn(generatedEmployeePayload);

        mockMvc.perform(post("/api/employee/create")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2MjAwMjUxNDYsImV4cCI6MTYyMDYyOTk0Nn0.gqZz2_6zNf8k-1ev_dc4tfMmU2d8Y2g5REBYl-cxItN2rCEIexup14x1rwGCVE1R23rcYJQRoKjHlaN18KS_Ug")
                .content(TestUtil.convertObjectToJsonBytes(employeePayload)))
                .andExpect(status().isCreated());

    }

    @Test
    public void updateEmployeeTest() throws Exception {
        when(employeeService.updateEmployee(generatedEmployeePayload)).thenReturn(generatedEmployeePayload);

        mockMvc.perform(put("/api/employee/update")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2MjAwMjUxNDYsImV4cCI6MTYyMDYyOTk0Nn0.gqZz2_6zNf8k-1ev_dc4tfMmU2d8Y2g5REBYl-cxItN2rCEIexup14x1rwGCVE1R23rcYJQRoKjHlaN18KS_Ug")
                .content(TestUtil.convertObjectToJsonBytes(generatedEmployeePayload)))
                .andExpect(status().isOk());
    }

    @Test
    public void fetchAllEmployeeTest() throws Exception {
        AppResponse response = new AppResponse();
        LinkedList<EmployeePayload> linkedList = new LinkedList<>();

        linkedList.add(generatedEmployeePayload);
        response.setEmployees(linkedList);

        when(employeeService.findAll()).thenReturn(response);

        // Get all the localGovtList
        mockMvc.perform(get("/api/employee/fetchAll")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2MjAwMjUxNDYsImV4cCI6MTYyMDYyOTk0Nn0.gqZz2_6zNf8k-1ev_dc4tfMmU2d8Y2g5REBYl-cxItN2rCEIexup14x1rwGCVE1R23rcYJQRoKjHlaN18KS_Ug"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.*.[*].employee_id").value(hasItem(generatedEmployeePayload.getEmployeeId())))
                .andExpect(jsonPath("$.*.[*].first_name").value(hasItem(generatedEmployeePayload.getFirstName())))
                .andExpect(jsonPath("$.*.[*].last_name").value(hasItem(generatedEmployeePayload.getLastName())))
                .andExpect(jsonPath("$.*.[*].age").value(hasItem(generatedEmployeePayload.getAge())));
    }

}
