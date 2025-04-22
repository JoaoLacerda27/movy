package com.movy.application.service;

import com.movy.application.domain.model.Delivery;
import com.movy.application.domain.model.Notification;
import com.movy.application.domain.model.User;
import com.movy.application.dto.NotificationDTO;
import com.movy.application.repository.NotificationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;


import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @Mock
    private NotificationRepository repository;

    @Mock
    private ModelMapper mapper;

    private NotificationService service;

    private UUID userId;
    private Notification notification;
    private NotificationDTO notificationDTO;
    private User sender;

    @BeforeEach
    void setUp() {
        service = new NotificationService(repository);

        ReflectionTestUtils.setField(service, "mapper", mapper);

        userId = UUID.randomUUID();
        sender = User.builder().id(userId).build();

        notification = Notification.builder()
                .id(UUID.randomUUID())
                .delivery(Delivery.builder().sender(sender).build())
                .message("Nova notificação")
                .createdAt(Instant.now())
                .read(false)
                .build();

        notificationDTO = new NotificationDTO();
    }


    @Test
    void getNotificationsByUserId_shouldReturnMappedDTOs() {
        when(repository.findByDeliverySenderId(userId))
                .thenReturn(List.of(notification));
        when(mapper.map(notification, NotificationDTO.class))
                .thenReturn(notificationDTO);

        var result = service.getNotificationsByUserId(userId);

        assertEquals(1, result.size());
        assertSame(notificationDTO, result.get(0));

        verify(repository).findByDeliverySenderId(userId);
        verify(mapper).map(notification, NotificationDTO.class);
    }

    @Test
    void markAsRead_shouldMarkNotificationAsReadAndSave() {
        UUID notificationId = notification.getId();
        when(repository.findById(notificationId)).thenReturn(Optional.of(notification));

        service.markAsRead(notificationId);

        assertTrue(notification.isRead());
        verify(repository).save(notification);
    }

    @Test
    void markAsRead_shouldNotSaveIfAlreadyRead() {
        UUID notificationId = notification.getId();
        notification.setRead(true);
        when(repository.findById(notificationId)).thenReturn(Optional.of(notification));

        service.markAsRead(notificationId);

        verify(repository, never()).save(notification);
    }

    @Test
    void markAsRead_shouldThrowIfNotificationNotFound() {
        UUID nonexistentId = UUID.randomUUID();
        when(repository.findById(nonexistentId)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> {
            service.markAsRead(nonexistentId);
        });

        assertEquals("Notificação não encontrada", ex.getMessage());
        verify(repository, never()).save(any());
    }
}
