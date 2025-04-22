package com.movy.application.controller.documentation;

import com.movy.application.controller.response.NotificationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@Tag(name = "Notification")
public interface NotificationDoc {
    @Operation(summary = "get all notifications by user")
    ResponseEntity<List<NotificationResponse>> getByUserId(UUID userId);

    @Operation(summary = "mark a notification as read")
    ResponseEntity<Void> markAsRead(UUID id);
}
