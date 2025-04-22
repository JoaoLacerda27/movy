package com.movy.application.service;

import com.movy.application.domain.model.Notification;
import com.movy.application.dto.NotificationDTO;
import com.movy.application.repository.NotificationRepository;
import com.movy.shared.services.ServiceBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService extends ServiceBase {

    private final NotificationRepository repository;

    public List<NotificationDTO> getNotificationsByUserId(UUID userId) {
        List<Notification> notifications = repository.findByDeliverySenderId(userId);
        return notifications.stream()
                .map(notification -> mapper.map(notification, NotificationDTO.class))
                .toList();
    }
}
