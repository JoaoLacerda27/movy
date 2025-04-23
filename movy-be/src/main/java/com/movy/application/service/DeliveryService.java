package com.movy.application.service;

import com.movy.application.domain.model.Delivery;
import com.movy.application.dto.DeliveryDTO;
import com.movy.application.repository.DeliveryRepository;
import com.movy.shared.services.ServiceBase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryService extends ServiceBase {

    private final DeliveryRepository repository;

    public DeliveryDTO newDelivery(DeliveryDTO dto) {
        Delivery delivery = mapper.map(dto, Delivery.class);

        Delivery saved = repository.save(delivery);

        return mapper.map(saved, DeliveryDTO.class);
    }

    public DeliveryDTO findByTrackingCode(String trackingCode) {
        Delivery delivery= repository.findByTrackingCode(trackingCode)
                .orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada."));

        return mapper.map(delivery, DeliveryDTO.class);
    }

    public List<DeliveryDTO> getDeliveries(UUID senderId, UUID recipientId, Instant startDate, Instant endDate, String status) {
        List<Delivery> deliveries = repository.findDeliveriesWithFilters(senderId, recipientId, startDate, endDate, status);
        return deliveries.stream()
                .map(delivery -> mapper.map(delivery, DeliveryDTO.class))
                .toList();
    }
}
