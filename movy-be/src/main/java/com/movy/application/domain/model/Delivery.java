package com.movy.application.domain.model;

import com.movy.application.domain.enums.DeliveryStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Entity
@Builder
@Table(name = "deliveries",
        uniqueConstraints = {
            @UniqueConstraint(name = "UC_DELIVERY__TRACKING_CODE", columnNames = "tracking_code")
        })
public class Delivery {
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
    @Column(name = "tracking_code", unique = true)
    private String trackingCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "recipient_id", foreignKey = @ForeignKey(name = "FK_DELIVERY__RECIPIENT_ID"))
    private Recipient recipient;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id", foreignKey = @ForeignKey(name = "FK_DELIVERY__USER_ID"))
    private User sender;
}
