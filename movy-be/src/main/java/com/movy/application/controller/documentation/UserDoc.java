package com.movy.application.controller.documentation;

import com.movy.application.controller.request.UserRequest;
import com.movy.application.controller.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@Tag(name = "User")
public interface UserDoc {
    @Operation(summary = "Cria um novo usuário")
    ResponseEntity<UserResponse> createUser(UserRequest request);

    @Operation(summary = "Retorna todos os usuários")
    ResponseEntity<List<UserResponse>> getAllUsers();

    @Operation(summary = "Busca um usuário pelo ID")
    ResponseEntity<UserResponse> getUserById(UUID id);

    @Operation(summary = "Atualiza um usuário existente")
    ResponseEntity<UserResponse> updateUser(UUID id, UserRequest request);

    @Operation(summary = "Remove um usuário pelo ID")
    ResponseEntity<Void> deleteUser(UUID id);
}
