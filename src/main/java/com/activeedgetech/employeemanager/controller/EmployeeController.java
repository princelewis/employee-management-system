package com.activeedgetech.employeemanager.controller;

import com.activeedgetech.employeemanager.dto.EmployeePayload;
import com.activeedgetech.employeemanager.dto.response.AppResponse;
import com.activeedgetech.employeemanager.exception.BadRequestException;
import com.activeedgetech.employeemanager.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * REST controller for managing {@link com.activeedgetech.employeemanager.model.Employee}
 */
@RestController
@Slf4j
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     *
     * {@code POST /create} : Method handler for creating a new employee
     *
     * @param request the employee payload to create
     * @return the {@link ResponseEntity} with status {@code 201 (created)} and with
     * the body of the newly created employee details, or with status {@code 400 (Bad Request)} if
     * employee request already has an id
     *
     * @throws  URISyntaxException if the location URI syntax is incorrect.
     */
    @PostMapping("/create")
    public ResponseEntity<AppResponse> createEmployee(@Valid @RequestBody EmployeePayload request) throws URISyntaxException {

        log.debug("REST request to save Employee : {}", request);
        if (request.getEmployeeId() != null) {
            throw new BadRequestException("A new Employee cannot already have an ID");
        }

        AppResponse response = employeeService.createEmployee(request);

        return ResponseEntity.created(new URI("/employee/" + response.getEmployees().get(0).getEmployeeId()))
                .body(response);

    }

    @PutMapping("/update")
    public ResponseEntity<AppResponse> updateEmployee(@Valid @RequestBody EmployeePayload request) {
         log.info("REST request to update Employee : {}", request);

         if (request.getEmployeeId() == null) {
             throw new BadRequestException("To update an employee you need to add an ID");
         }

         AppResponse response = employeeService.updateEmployee(request);
        return ResponseEntity.ok().body(response);
    }
}
