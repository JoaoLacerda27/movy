package com.movy.application.controller.response;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class RecipientResponse {
    private UUID id;
    private String name;
    private String address;
    private Instant createdAt;
}
