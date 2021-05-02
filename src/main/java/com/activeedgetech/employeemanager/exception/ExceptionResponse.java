package com.activeedgetech.employeemanager.exception;

import lombok.Data;

import java.time.Instant;

@Data
public class ExceptionResponse {

    private Instant date;
    private String message;

    public ExceptionResponse(Instant date, String message){
        this.date = date;
        this.message = message;
    }

}
