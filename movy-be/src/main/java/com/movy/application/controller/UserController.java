package com.movy.application.controller;

import com.movy.application.controller.documentation.UserDoc;
import com.movy.application.controller.request.UserRequest;
import com.movy.application.controller.response.UserResponse;
import com.movy.application.dto.UserDTO;
import com.movy.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/users")
public class UserController implements UserDoc {

    private final UserService service;
    private final ModelMapper mapper;

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        UserDTO createdUser = service.createUser(mapper.map(request, UserDTO.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(createdUser, UserResponse.class));
    }
}
