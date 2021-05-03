package com.activeedgetech.employeemanager.controller;

import com.activeedgetech.employeemanager.dto.response.JwtAuthResponse;
import com.activeedgetech.employeemanager.security.JwtTokenProvider;
import com.activeedgetech.employeemanager.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/getToken")
    public ResponseEntity<JwtAuthResponse> generateToken(){
        String token = jwtTokenProvider.generateToken();

        JwtAuthResponse response = new JwtAuthResponse();
        response.setMessage(AppConstant.JWT_SUCCESS_MESSAGE);
        response.setToken(token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
