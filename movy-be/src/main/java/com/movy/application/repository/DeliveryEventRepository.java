package com.movy.application.repository;

import com.movy.application.domain.model.DeliveryEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DeliveryEventRepository extends JpaRepository<DeliveryEvent, UUID> {
    List<DeliveryEvent> findByDeliveryId(UUID deliveryId);
}
