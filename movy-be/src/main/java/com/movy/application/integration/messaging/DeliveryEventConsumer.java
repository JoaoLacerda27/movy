package com.movy.application.integration.messaging;

import com.movy.application.domain.model.Delivery;
import com.movy.application.domain.model.Notification;
import com.movy.application.dto.DeliveryEventDTO;
import com.movy.application.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.movy.shared.configuration.RabbitMQConfiguration.DELIVERY_EVENTS_QUEUE;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeliveryEventConsumer {

    private final NotificationRepository notificationRepository;

    private final ModelMapper mapper;

    @RabbitListener(queues = DELIVERY_EVENTS_QUEUE)
    public void consume(DeliveryEventDTO dto) {
        log.info("Mensagem recebida da fila: {}", dto);

        Notification notification = Notification.builder()
                .delivery(mapper.map(dto.getDelivery(), Delivery.class))
                .message("Novo evento de entrega: " + dto.getEventType())
                .createdAt(dto.getTimestamp())
                .build();

        notificationRepository.save(notification);
    }
}
