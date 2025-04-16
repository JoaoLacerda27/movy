package com.movy.application.service;

import com.movy.application.domain.model.Delivery;
import com.movy.application.domain.model.DeliveryEvent;
import com.movy.application.dto.DeliveryEventDTO;
import com.movy.application.repository.DeliveryEventRepository;
import com.movy.application.repository.DeliveryRepository;
import com.movy.shared.services.ServiceBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryEventService extends ServiceBase {

    private final DeliveryEventRepository repository;

    private final DeliveryRepository deliveryRepository;

    public DeliveryEventDTO registerEvent(DeliveryEventDTO dto) {
        Delivery delivery = deliveryRepository.findById(dto.getDelivery().getId())
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        DeliveryEvent event = mapper.map(dto, DeliveryEvent.class);
        event.setDelivery(delivery);

        DeliveryEvent saved = repository.save(event);
        return mapper.map(saved, DeliveryEventDTO.class);
    }
}
