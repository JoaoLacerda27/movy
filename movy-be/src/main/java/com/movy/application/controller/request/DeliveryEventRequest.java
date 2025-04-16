package com.movy.application.controller.request;

import com.movy.application.domain.enums.DeliveryEventType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class DeliveryEventRequest {
    @NotNull
    private DeliveryEventType eventType;
    @NotBlank
    private String description;
    private String location;
    private UUID eventId;

}
