package com.example.ecommercespringboot.dto;

import com.example.ecommercespringboot.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String email;

    private String name;

    private UserRole userRole;

}
