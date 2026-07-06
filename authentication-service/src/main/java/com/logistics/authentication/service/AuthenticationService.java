package com.logistics.authentication.service;


import com.logistics.authentication.dto.RegisterRequest;
import com.logistics.authentication.dto.UserResponse;

public interface AuthenticationService {

    UserResponse register(RegisterRequest request);
}
