package com.movy.application.service;

import com.movy.application.domain.model.Notification;
import com.movy.application.dto.NotificationDTO;
import com.movy.application.repository.NotificationRepository;
import com.movy.shared.exceptions.types.BusinessException;
import com.movy.shared.exceptions.types.ResourceNotFoundException;
import com.movy.shared.services.ServiceBase;
import jakarta.transaction.Transactional;
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
        if (notifications.isEmpty()) {
            throw new ResourceNotFoundException("Notificações não encontradas.");
        }
        return notifications.stream()
                .map(notification -> mapper.map(notification, NotificationDTO.class))
                .toList();
    }

    @Transactional
    public void markAsRead(UUID id) {
        Notification notification = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notificação não encontrada"));

        if (!notification.isRead()) {
            notification.setRead(true);

            try {
                repository.save(notification);
            } catch (Exception e) {
                throw new BusinessException("Erro ao marcar notificação como lida.");
            }
        }
    }
}
