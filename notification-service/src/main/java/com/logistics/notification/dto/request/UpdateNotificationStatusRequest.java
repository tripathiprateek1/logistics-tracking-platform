package com.logistics.notification.dto.request;

import com.logistics.notification.entity.NotificationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNotificationStatusRequest {

    @NotNull(message = "Status is required")
    private NotificationStatus status;
}
