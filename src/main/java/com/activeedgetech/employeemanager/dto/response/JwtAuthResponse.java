package com.activeedgetech.employeemanager.dto.response;

import lombok.Data;

@Data
public class JwtAuthResponse {

    private String message;
    private String token;
}
