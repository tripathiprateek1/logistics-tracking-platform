package com.logistics.notification.controller;

import com.logistics.notification.dto.request.CreateNotificationRequest;
import com.logistics.notification.dto.request.UpdateNotificationStatusRequest;
import com.logistics.notification.dto.response.NotificationResponse;
import com.logistics.notification.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public NotificationResponse createNotification(
            @Valid @RequestBody CreateNotificationRequest request) {

        return notificationService.createNotification(request);
    }

    @GetMapping("/{id}")
    public NotificationResponse getNotificationById(
            @PathVariable Long id) {

        return notificationService.getNotificationById(id);
    }

    @GetMapping
    public List<NotificationResponse> getAllNotifications() {

        return notificationService.getAllNotifications();
    }

    @PutMapping("/{id}/status")
    public NotificationResponse updateNotificationStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateNotificationStatusRequest request) {

        return notificationService.updateNotificationStatus(id, request);
    }

}

