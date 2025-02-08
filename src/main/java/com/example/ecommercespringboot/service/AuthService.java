package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.dto.SignupRequest;
import com.example.ecommercespringboot.dto.UserDto;

public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);

    Boolean hasUserWithEmail(String email);

}
