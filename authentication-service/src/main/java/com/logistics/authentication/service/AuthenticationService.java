package com.logistics.authentication.service;


import com.logistics.authentication.dto.request.LoginRequest;
import com.logistics.authentication.dto.request.RegisterRequest;
import com.logistics.authentication.dto.response.LoginResponse;
import com.logistics.authentication.dto.response.UserResponse;

public interface AuthenticationService {

    UserResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
