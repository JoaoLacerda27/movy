package com.movy.application.dto;

import com.movy.application.domain.enums.DeliveryStatus;
import com.movy.shared.dtos.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DeliveryDTO extends DtoBase {
    private String trackingCode;
    private RecipientDTO recipient;
    private DeliveryStatus status;
    private UserDTO sender;
}
