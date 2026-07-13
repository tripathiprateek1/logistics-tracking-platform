package com.logistics.shipment.controller;

import com.logistics.shipment.dto.request.CreateShipmentRequest;
import com.logistics.shipment.dto.request.UpdateShipmentStatusRequest;
import com.logistics.shipment.dto.response.ShipmentResponse;
import com.logistics.shipment.service.ShipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipments")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShipmentResponse createShipment(
            @Valid @RequestBody CreateShipmentRequest request) {

        return shipmentService.createShipment(request);
    }

    @GetMapping("/{trackingNumber}")
    public ShipmentResponse getShipment(
            @PathVariable String trackingNumber) {

        return shipmentService.getShipmentByTrackingNumber(trackingNumber);
    }

    @PutMapping("/{trackingNumber}/status")
    public ShipmentResponse updateShipmentStatus(
            @PathVariable String trackingNumber,
            @Valid @RequestBody UpdateShipmentStatusRequest request) {

        return shipmentService.updateShipmentStatus(
                trackingNumber,
                request);
    }

    @GetMapping
    public List<ShipmentResponse> getAllShipments() {

        return shipmentService.getAllShipments();
    }
}
