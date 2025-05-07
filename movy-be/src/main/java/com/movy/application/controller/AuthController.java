package com.movy.application.controller;

import com.movy.application.controller.documentation.AuthDoc;
import com.movy.application.controller.request.LoginRequest;
import com.movy.application.controller.response.LoginResponse;
import com.movy.application.repository.UserRepository;
import com.movy.shared.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthDoc {

    private final AuthService authService;
    private final UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        var user = repository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        LoginResponse response = new LoginResponse(
                token,
                "Bearer",
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity.ok(response);
    }
}
