package com.movy.application.repository.custom.Delivery;

import com.movy.application.domain.model.Delivery;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface DeliveryRepositoryCustom {
    List<Delivery> findDeliveriesWithFilters(UUID senderId, UUID recipientId, Instant startDate, Instant endDate, String status);
}
