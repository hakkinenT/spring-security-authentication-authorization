package com.hakkinenT.authentication.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hakkinenT.authentication.dto.AuthenticationRequestDTO;
import com.hakkinenT.authentication.dto.AuthenticationResponseDTO;
import com.hakkinenT.authentication.dto.UserRegisterDTO;
import com.hakkinenT.authentication.service.AuthenticationService;



@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponseDTO register(@RequestBody UserRegisterDTO dto){
        return service.register(dto);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponseDTO authenticate(@RequestBody AuthenticationRequestDTO dto){
        return service.authenticate(dto);
    }

}
