package com.movy.application.integration.messaging;

import com.movy.application.dto.DeliveryEventDTO;
import com.movy.shared.configuration.RabbitMQConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public void publish(DeliveryEventDTO dto) {
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.DELIVERY_EVENTS_QUEUE, dto);
    }
}
