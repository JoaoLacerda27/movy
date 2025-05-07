package com.movy.application.controller.documentation;

import com.movy.application.controller.request.UserRequest;
import com.movy.application.controller.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "User")
public interface UserDoc {
    @Operation(summary = "create an user")
    ResponseEntity<UserResponse> createUser(UserRequest request);
}
