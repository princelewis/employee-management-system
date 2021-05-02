package com.activeedgetech.employeemanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Instant;

/**
 * Handle all the uncaught exceptions
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> methodArgumentNotValidHandler(MethodArgumentNotValidException ex) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(Instant.now(), ex.getFieldError().getDefaultMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> integrityViolationHandler(SQLIntegrityConstraintViolationException ex) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(Instant.now(), ErrorMessage.DUPLICATE_ENTRY.getValue());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ExceptionResponse> badRequestHandler(BadRequestException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(Instant.now(), ex.getMessage());
        return  new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
