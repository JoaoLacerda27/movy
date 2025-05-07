package com.movy.application.service;

import com.movy.application.domain.enums.UserRole;
import com.movy.application.domain.model.User;
import com.movy.application.dto.UserDTO;
import com.movy.application.repository.UserRepository;
import com.movy.shared.services.ServiceBase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService extends ServiceBase {

    private final UserRepository repository;

    private final PasswordEncoder encoder;

    public UserDTO createUser(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole(UserRole.ROLE_USER);

        var userSaved = repository.save(user);

        return mapper.map(userSaved, UserDTO.class);

    }
}
