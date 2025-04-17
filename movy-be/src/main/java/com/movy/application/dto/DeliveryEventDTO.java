package com.movy.application.dto;

import com.movy.application.domain.enums.DeliveryEventType;
import com.movy.shared.dtos.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DeliveryEventDTO extends DtoBase {
    private DeliveryDTO delivery;
    private DeliveryEventType eventType;
    private String description;
    private String location;
    private Instant timestamp;
}
