package com.movy.application.controller.response;

import com.movy.application.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {
    private String token;
    private String tokenType;
    private String name;
    private String email;
    private UserRole role;
}
