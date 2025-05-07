package com.movy.application.controller.documentation;

import com.movy.application.controller.request.LoginRequest;
import com.movy.application.controller.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Authentication", description = "API para autenticação de usuários")
public interface AuthDoc {

    @Operation(
            summary = "Login de usuário",
            description = "Realiza o login de um usuário e retorna um token JWT válido.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Login bem-sucedido, retorna um token JWT.",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = LoginResponse.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Credenciais inválidas")
            }
    )
    ResponseEntity<LoginResponse> login(LoginRequest loginRequest);
}
