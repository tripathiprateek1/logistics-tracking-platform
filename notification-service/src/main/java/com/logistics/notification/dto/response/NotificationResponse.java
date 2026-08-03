package com.logistics.notification.dto.response;


import com.logistics.notification.entity.NotificationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

    private Long id;

    private String recipientEmail;

    private String subject;

    private String message;

    private NotificationStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
