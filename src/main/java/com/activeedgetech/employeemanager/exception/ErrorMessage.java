package com.activeedgetech.employeemanager.exception;

public enum ErrorMessage {
    DUPLICATE_ENTRY("This employee has already been created"),
    INVALID_EMPLOYEE_ID("You have exceeded the provisioned employee Id");

    private String value;
    ErrorMessage(String value) {

        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
