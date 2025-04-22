package com.movy.application.integration.messaging;

import com.movy.application.domain.model.Delivery;
import com.movy.application.domain.model.Notification;
import com.movy.application.dto.DeliveryEventDTO;
import com.movy.application.repository.NotificationRepository;
import com.movy.shared.logging.service.LogService;
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
    private final LogService logService;

    @RabbitListener(queues = DELIVERY_EVENTS_QUEUE)
    public void consume(DeliveryEventDTO dto) {
        log.info("Mensagem recebida da fila: {}", dto);

        Delivery delivery = mapper.map(dto.getDelivery(), Delivery.class);
        String message = "Novo evento de entrega: " + dto.getEventType();

        boolean exists = notificationRepository.existsByDeliveryIdAndMessage(delivery.getId(), message);
        if (exists) {
            log.info("Notificação duplicada ignorada para delivery={} com mensagem='{}'", delivery.getId(), message);
            return;
        }

        Notification notification = Notification.builder()
                .delivery(mapper.map(dto.getDelivery(), Delivery.class))
                .message("Novo evento de entrega: " + dto.getEventType())
                .createdAt(dto.getTimestamp())
                .build();

        notificationRepository.save(notification);

        logService.log(
                "CONSUME_DELIVERY_EVENT",
                this.getClass().getSimpleName(),
                "consume",
                "ADMIN",
                "Evento de entrega processado e notificação gerada",
                dto
        );
    }
}
