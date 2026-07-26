package com.logistics.notification.repository;

import com.logistics.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Add custom query methods here if needed

}

