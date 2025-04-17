package com.movy.application.controller.response;

import com.movy.application.domain.enums.DeliveryStatus;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class DeliveryResponse {

    private UUID id;
    private Instant createdAt;
    private String trackingCode;
    private DeliveryStatus status;
    private RecipientResponse recipient;
    private UserResponse user;
}
