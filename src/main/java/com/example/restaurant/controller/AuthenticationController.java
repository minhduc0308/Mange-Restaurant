package com.example.restaurant.controller;

import com.example.restaurant.dto.request.AuthenticationRequest; // Import đúng lớp DTO của bạn
import com.example.restaurant.dto.request.IntrospectRequest;
import com.example.restaurant.dto.response.ApiResponse;
import com.example.restaurant.dto.response.AuthenticationResponse; // Import đúng lớp DTO của bạn
import com.example.restaurant.dto.response.IntrospectResponse;
import com.example.restaurant.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    // Đổi thành đúng AuthenticationRequest từ DTO của bạn
    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        // Gọi phương thức authenticate từ service với DTO AuthenticationRequest của bạn
        var result = authenticationService.authenticate(authenticationRequest);

        // Trả về ApiResponse với AuthenticationResponse từ DTO của bạn
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        var result = authenticationService.introspect(introspectRequest);

        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }
}
