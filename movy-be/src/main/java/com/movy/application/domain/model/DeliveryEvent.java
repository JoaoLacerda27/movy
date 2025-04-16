package com.movy.application.domain.model;

import com.movy.application.domain.enums.DeliveryEventType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "delivery_events")
public class DeliveryEvent {
    @Id
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid2")
    @NotNull
    protected UUID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id", foreignKey = @ForeignKey(name = "FK_DELIVERY_EVENT__DELIVERY_ID"))
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private DeliveryEventType eventType;

    @Column
    private String description;

    @Column
    private String location;

    @CreationTimestamp
    private Instant timestamp;
}