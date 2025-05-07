package com.movy.application.service;

import com.movy.application.domain.enums.UserRole;
import com.movy.application.domain.model.User;
import com.movy.application.dto.UserDTO;
import com.movy.application.repository.UserRepository;
import com.movy.shared.exceptions.types.ResourceNotFoundException;
import com.movy.shared.services.ServiceBase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public List<UserDTO> getAllUsers() {
        return repository.findAll().stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(UUID id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
        return mapper.map(user, UserDTO.class);
    }

    public UserDTO updateUser(UUID id, UserDTO dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        if (dto.getPassword() != null) {
            user.setPassword(encoder.encode(dto.getPassword()));
        }
        user.setRole(dto.getRole() != null ? dto.getRole() : user.getRole());
        return mapper.map(repository.save(user), UserDTO.class);
    }

    public void deleteUser(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado.");
        }
        repository.deleteById(id);
    }
}
