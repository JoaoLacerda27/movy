package com.movy.application.controller;

import com.movy.application.controller.documentation.AuthDoc;
import com.movy.application.controller.request.LoginRequest;
import com.movy.application.controller.request.RefreshTokenRequest;
import com.movy.application.controller.response.LoginResponse;
import com.movy.application.domain.enums.UserRole;
import com.movy.application.domain.model.User;
import com.movy.application.repository.UserRepository;
import com.movy.shared.security.model.TokenResponse;
import com.movy.shared.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/auth")
public class AuthController implements AuthDoc {

    private final AuthService authService;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        TokenResponse tokenResponse = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        var user = repository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        LoginResponse response = new LoginResponse(
                tokenResponse.getAccessToken(),
                tokenResponse.getTokenType(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        TokenResponse tokenResponse = authService.refreshToken(request.getRefreshToken());
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/create-admin")
    public ResponseEntity<Void> createAdmin() {
        if (repository.findByEmail("admin@movy.com").isPresent()) {
            return ResponseEntity.ok().build();
        }

        User admin = new User();
        admin.setId(UUID.randomUUID());
        admin.setName("Admin");
        admin.setEmail("admin@movy.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole(UserRole.ROLE_ADMIN);

        repository.save(admin);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/debug/user/{email}")
    public ResponseEntity<Map<String, Object>> debugUser(@PathVariable String email) {
        var user = repository.findByEmail(email);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("exists", true);
        response.put("name", user.get().getName());
        response.put("email", user.get().getEmail());
        response.put("role", user.get().getRole());
        response.put("password", user.get().getPassword());
        response.put("passwordMatches", passwordEncoder.matches("admin123", user.get().getPassword()));

        return ResponseEntity.ok(response);
    }
}
