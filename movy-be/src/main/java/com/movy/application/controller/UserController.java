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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/users")
public class UserController implements UserDoc {

    private final UserService service;
    private final ModelMapper mapper;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        UserDTO createdUser = service.createUser(mapper.map(request, UserDTO.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(createdUser, UserResponse.class));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserDTO> users = service.getAllUsers();
        return ResponseEntity.ok(users.stream()
                .map(user -> mapper.map(user, UserResponse.class))
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        UserDTO user = service.getUserById(id);
        return ResponseEntity.ok(mapper.map(user, UserResponse.class));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @RequestBody UserRequest request) {
        UserDTO updated = service.updateUser(id, mapper.map(request, UserDTO.class));
        return ResponseEntity.ok(mapper.map(updated, UserResponse.class));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
