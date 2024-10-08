package com.example.restaurant.controller;

import com.example.restaurant.dto.request.UserCreationRequest;
import com.example.restaurant.dto.response.ApiResponse;
import com.example.restaurant.entities.Users;
import com.example.restaurant.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ApiResponse<Users> createUser(@RequestBody UserCreationRequest userCreationRequest){
        ApiResponse<Users> apiResponse = new ApiResponse<>();
        try {
            Users createdUser = userService.createUser(userCreationRequest);
            apiResponse.setMessage("Create User Successfully!");
            apiResponse.setResult(createdUser);
        } catch (Exception e) {
            apiResponse.setMessage("Error occurred: " + e.getMessage());
            apiResponse.setResult(null);
        }
        return apiResponse;
    }

}

