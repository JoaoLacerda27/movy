package com.movy.application.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "users",
        uniqueConstraints = {
            @UniqueConstraint(name = "UC_USER__EMAIL", columnNames = "email")
        })
public class User {
    @Id
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid2")
    @NotNull
    protected UUID id;

    @CreationTimestamp
    @Column
    private Instant createdAt;

    @NotBlank
    @Size(max = 120)
    @Column
    private String name;

    @NotBlank
    @Size(max = 120)
    @Email
    @Column
    private String email;

    @NotBlank
    @Size(max = 120)
    @Column
    private String password;

    @Size(max = 20)
    @Column
    private String phone;

    @NotBlank
    @Size(max = 120)
    @Column
    private String role;
}
