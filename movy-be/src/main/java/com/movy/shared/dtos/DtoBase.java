package com.movy.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public abstract class DtoBase {
    private UUID id;
    private Instant createdAt;

    public DtoBase(UUID id) {
        this.id = id;
    }
}
