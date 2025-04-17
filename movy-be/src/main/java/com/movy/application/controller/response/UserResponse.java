package com.movy.application.controller.response;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    private Instant createdAt;
    private String name;
    private String email;
    private String phone;
    private String role;
}
