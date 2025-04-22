package com.movy.application.controller;

import com.movy.application.controller.response.NotificationResponse;
import com.movy.application.dto.NotificationDTO;
import com.movy.application.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/notifications")
public class NotificationController {

    private final NotificationService service;
    private final ModelMapper mapper;

    public ResponseEntity<List<NotificationResponse>> getByUserId(@RequestParam UUID userId) {
        List<NotificationDTO> notifications = service.getNotificationsByUserId(userId);

        List<NotificationResponse> response = notifications.stream()
                .map(notification -> mapper.map(notification, NotificationResponse.class))
                .toList();

        return ResponseEntity.ok(response);
    }
}
