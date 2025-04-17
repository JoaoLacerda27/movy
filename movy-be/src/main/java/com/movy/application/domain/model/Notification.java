package com.movy.application.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid2")
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    protected UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "delivery_id", foreignKey = @ForeignKey(name = "FK_NOTIFICATION__DELIVERY_ID"))
    private Delivery delivery;

    @Column(nullable = false, columnDefinition = "text")
    private String message;

    @Column(nullable = false)
    private boolean read = false;

    @CreationTimestamp
    private Instant createdAt;
}
