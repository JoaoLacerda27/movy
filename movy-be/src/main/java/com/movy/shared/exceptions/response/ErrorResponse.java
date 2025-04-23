package com.movy.shared.exceptions.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ErrorResponse {
    private String errorCode;
    private String message;
}
