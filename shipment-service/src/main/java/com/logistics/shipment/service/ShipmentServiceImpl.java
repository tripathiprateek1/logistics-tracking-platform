package com.logistics.shipment.service;

import com.logistics.shipment.dto.request.CreateShipmentRequest;
import com.logistics.shipment.dto.request.UpdateShipmentStatusRequest;
import com.logistics.shipment.dto.response.ShipmentResponse;
import com.logistics.shipment.entity.Shipment;
import com.logistics.shipment.entity.ShipmentStatus;
import com.logistics.shipment.exception.ResourceNotFoundException;
import com.logistics.shipment.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl  implements ShipmentService {

    private final ShipmentRepository shipmentRepository;

    @Override
    public ShipmentResponse createShipment(CreateShipmentRequest request) {
        Shipment shipment = Shipment.builder()
                .trackingNumber(generateTrackingNumber())
                .senderName(request.getSenderName())
                .receiverName(request.getReceiverName())
                .origin(request.getOrigin())
                .destination(request.getDestination())
                .status(ShipmentStatus.CREATED)
                .build();

        Shipment savedShipment = shipmentRepository.save(shipment);

        return ShipmentResponse.builder()
                .trackingNumber(savedShipment.getTrackingNumber())
                .senderName(savedShipment.getSenderName())
                .receiverName(savedShipment.getReceiverName())
                .origin(savedShipment.getOrigin())
                .destination(savedShipment.getDestination())
                .status(savedShipment.getStatus())
                .createdAt(savedShipment.getCreatedAt())
                .updatedAt(savedShipment.getUpdatedAt())
                .build();
    }

    @Override
    public ShipmentResponse getShipmentByTrackingNumber(String trackingNumber) {

        Shipment shipment = shipmentRepository
                .findByTrackingNumber(trackingNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Shipment not found with tracking number: "
                                        + trackingNumber));

        return ShipmentResponse.builder()
                .trackingNumber(shipment.getTrackingNumber())
                .senderName(shipment.getSenderName())
                .receiverName(shipment.getReceiverName())
                .origin(shipment.getOrigin())
                .destination(shipment.getDestination())
                .status(shipment.getStatus())
                .createdAt(shipment.getCreatedAt())
                .updatedAt(shipment.getUpdatedAt())
                .build();
    }

    @Override
    public ShipmentResponse updateShipmentStatus(String trackingNumber, UpdateShipmentStatusRequest request) {
        Shipment shipment = shipmentRepository
                .findByTrackingNumber(trackingNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Shipment not found with tracking number: "
                                        + trackingNumber));

        shipment.setStatus(request.getStatus());

        Shipment updatedShipment = shipmentRepository.save(shipment);

        return ShipmentResponse.builder()
                .trackingNumber(updatedShipment.getTrackingNumber())
                .senderName(updatedShipment.getSenderName())
                .receiverName(updatedShipment.getReceiverName())
                .origin(updatedShipment.getOrigin())
                .destination(updatedShipment.getDestination())
                .status(updatedShipment.getStatus())
                .createdAt(updatedShipment.getCreatedAt())
                .updatedAt(updatedShipment.getUpdatedAt())
                .build();
    }

    @Override
    public List<ShipmentResponse> getAllShipments() {
        return shipmentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private String generateTrackingNumber() {
        return "TRK" + System.currentTimeMillis();
    }
    private ShipmentResponse mapToResponse(Shipment shipment) {

        return ShipmentResponse.builder()
                .trackingNumber(shipment.getTrackingNumber())
                .senderName(shipment.getSenderName())
                .receiverName(shipment.getReceiverName())
                .origin(shipment.getOrigin())
                .destination(shipment.getDestination())
                .status(shipment.getStatus())
                .createdAt(shipment.getCreatedAt())
                .updatedAt(shipment.getUpdatedAt())
                .build();
    }
}
