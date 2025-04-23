package com.movy.application.service;

import com.movy.application.domain.model.Delivery;
import com.movy.application.domain.model.DeliveryEvent;
import com.movy.application.dto.DeliveryEventDTO;
import com.movy.application.integration.messaging.DeliveryEventProducer;
import com.movy.application.repository.DeliveryEventRepository;
import com.movy.application.repository.DeliveryRepository;
import com.movy.shared.services.ServiceBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryEventService extends ServiceBase {

    private final DeliveryEventRepository repository;

    private final DeliveryRepository deliveryRepository;

    private final DeliveryEventProducer deliveryEventProducer;

    public DeliveryEventDTO registerEvent(DeliveryEventDTO dto) {
        Delivery delivery = deliveryRepository.findById(dto.getDelivery().getId())
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        DeliveryEvent event = mapper.map(dto, DeliveryEvent.class);
        event.setDelivery(delivery);

        DeliveryEvent saved = repository.save(event);

        var response = mapper.map(saved, DeliveryEventDTO.class);

        deliveryEventProducer.publish(response);

        return response;
    }

    public List<DeliveryEventDTO> findByDeliveryId(UUID deliveryId) {
        List<DeliveryEvent> events = repository.findByDeliveryId(deliveryId);

        return events.stream()
                .map(event -> mapper.map(event, DeliveryEventDTO.class))
                .toList();
    }
}
