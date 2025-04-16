package com.movy.application.dto;

import com.movy.shared.dtos.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RecipientDTO extends DtoBase {
    private String name;
    private String address;
}
