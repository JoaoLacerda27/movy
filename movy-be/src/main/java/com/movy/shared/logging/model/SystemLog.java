package com.movy.shared.logging.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "system_logs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SystemLog {
    @Id
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid2")
    @NotNull
    private UUID id;

    @Column
    private String action;

    @Column
    private String className;

    @Column
    private String methodName;

    @Column
    private String username;

    @Column
    private String message;

    @Column
    private Instant timestamp;

    @Column(columnDefinition = "TEXT")
    private String payload;

}
