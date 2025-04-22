package com.movy.application.controller.response;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class NotificationResponse {
    private UUID id;
    private DeliveryResponse delivery;
    private String message;
    private boolean read;
    private Instant createdAt;
}
