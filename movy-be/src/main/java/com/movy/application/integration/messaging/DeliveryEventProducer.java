package com.movy.application.integration.messaging;

import com.movy.application.dto.DeliveryEventDTO;
import com.movy.shared.configuration.RabbitMQConfiguration;
import com.movy.shared.logging.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryEventProducer {

    private final RabbitTemplate rabbitTemplate;
    private final LogService logService;

    public void publish(DeliveryEventDTO dto) {
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.DELIVERY_EVENTS_QUEUE, dto);

        logService.log(
                "PUBLISH_DELIVERY_EVENT",
                this.getClass().getSimpleName(),
                "publish",
                "ADMIN",
                "Novo evento de entrega publicado na fila",
                dto
        );
    }
}
