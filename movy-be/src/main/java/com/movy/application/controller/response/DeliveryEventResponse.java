package com.movy.application.controller.response;

import com.movy.application.domain.enums.DeliveryEventType;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class DeliveryEventResponse {
    private UUID id;
    private DeliveryEventType eventType;
    private String description;
    private String location;
    private Instant timestamp;
    private DeliveryResponse delivery;
}
