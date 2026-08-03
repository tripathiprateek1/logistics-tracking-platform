package com.logistics.notification.service;

import com.logistics.notification.dto.request.CreateNotificationRequest;
import com.logistics.notification.dto.request.UpdateNotificationStatusRequest;
import com.logistics.notification.dto.response.NotificationResponse;
import com.logistics.notification.entity.Notification;
import com.logistics.notification.exception.ResourceNotFoundException;
import com.logistics.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public NotificationResponse createNotification(CreateNotificationRequest request) {

        Notification notification = Notification.builder()
                .recipientEmail(request.getRecipientEmail())
                .subject(request.getSubject())
                .message(request.getMessage())
                .build();

        Notification savedNotification = notificationRepository.save(notification);

        return mapToResponse(savedNotification);
    }

    @Override
    public NotificationResponse getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() ->
                new ResourceNotFoundException("Notification not found with id: " + id));

        return mapToResponse(notification);
    }

    @Override
    public List<NotificationResponse> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationResponse updateNotificationStatus(Long id, UpdateNotificationStatusRequest request) {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() ->
                new ResourceNotFoundException("Notification not found with id: " + id));

        notification.setStatus(request.getStatus());

        Notification updatedNotification = notificationRepository.save(notification);

        return mapToResponse(updatedNotification);
    }

    private NotificationResponse mapToResponse(Notification notification) {

        return NotificationResponse.builder()
                .id(notification.getId())
                .recipientEmail(notification.getRecipientEmail())
                .subject(notification.getSubject())
                .message(notification.getMessage())
                .status(notification.getStatus())
                .createdAt(notification.getCreatedAt())
                .updatedAt(notification.getUpdatedAt())
                .build();
    }
}
