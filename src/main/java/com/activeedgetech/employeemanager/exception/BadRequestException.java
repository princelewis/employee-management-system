package com.activeedgetech.employeemanager.exception;

/**
 * An object of the RuntimeException
 * to throw in the case of a bad request
 *
 */
public class BadRequestException extends RuntimeException{

    public BadRequestException(String message) {
        super(message);
    }
}
