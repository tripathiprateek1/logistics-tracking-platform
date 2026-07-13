package com.logistics.shipment.service;

import com.logistics.shipment.dto.request.CreateShipmentRequest;
import com.logistics.shipment.dto.request.UpdateShipmentStatusRequest;
import com.logistics.shipment.dto.response.ShipmentResponse;

import java.util.List;

public interface ShipmentService {

  public ShipmentResponse createShipment(CreateShipmentRequest request);
    public ShipmentResponse getShipmentByTrackingNumber(String trackingNumber);

   public  ShipmentResponse updateShipmentStatus(
            String trackingNumber,
            UpdateShipmentStatusRequest request);
   public List<ShipmentResponse> getAllShipments();
}
