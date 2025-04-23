package com.movy.application.repository;

import com.movy.application.domain.model.Delivery;
import com.movy.application.repository.custom.Delivery.DeliveryRepositoryCustom;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID>, DeliveryRepositoryCustom {
    Optional<Delivery> findByTrackingCode(@NotBlank String trackingCode);
}
