package com.logistics.notification.service;

import com.logistics.notification.dto.request.CreateNotificationRequest;
import com.logistics.notification.dto.request.UpdateNotificationStatusRequest;
import com.logistics.notification.dto.response.NotificationResponse;
import java.util.List;


public interface NotificationService {



    NotificationResponse createNotification(CreateNotificationRequest request);

    NotificationResponse getNotificationById(Long id);

    List<NotificationResponse> getAllNotifications();

    NotificationResponse updateNotificationStatus(
            Long id,
            UpdateNotificationStatusRequest request);

}

