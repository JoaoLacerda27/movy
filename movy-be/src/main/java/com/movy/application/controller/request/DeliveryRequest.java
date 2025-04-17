package com.movy.application.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class DeliveryRequest {
    @NotBlank
    private String trackingCode;

    @NotNull
    private UUID recipientId;

    @NotNull
    private UUID senderId;
}
