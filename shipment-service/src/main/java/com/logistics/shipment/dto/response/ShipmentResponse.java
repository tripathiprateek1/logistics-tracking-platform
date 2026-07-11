package com.logistics.shipment.dto.response;


import com.logistics.shipment.entity.ShipmentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ShipmentResponse {

    private String trackingNumber;

    private String senderName;

    private String receiverName;

    private String origin;

    private String destination;

    private ShipmentStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
