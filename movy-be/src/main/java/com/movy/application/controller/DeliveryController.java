package com.movy.application.controller;

import com.movy.application.controller.documentation.DeliveryDoc;
import com.movy.application.controller.request.DeliveryRequest;
import com.movy.application.controller.response.DeliveryResponse;
import com.movy.application.domain.model.Delivery;
import com.movy.application.dto.DeliveryDTO;
import com.movy.application.service.DeliveryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/deliveries")
public class DeliveryController implements DeliveryDoc {

    private final DeliveryService service;

    private final ModelMapper mapper;

    @Override
    @PostMapping
    public ResponseEntity<DeliveryResponse> newDelivery(@RequestBody @Valid DeliveryRequest request) {
        DeliveryDTO delivery = service.newDelivery(mapper.map(request, DeliveryDTO.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(delivery, DeliveryResponse.class));
    }

    @Override
    @GetMapping("/track/{code}")
    public ResponseEntity<DeliveryResponse> getByTrackingCode(@PathVariable String code) {
        DeliveryDTO delivery = service.findByTrackingCode(code);
        DeliveryResponse response = mapper.map(delivery, DeliveryResponse.class);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<DeliveryResponse>> getDeliveries(
            @RequestParam(required = false) UUID senderId,
            @RequestParam(required = false) UUID recipientId,
            @RequestParam(required = false) Instant startDate,
            @RequestParam(required = false) Instant endDate,
            @RequestParam(required = false) String status) {

        List<DeliveryDTO> deliveries = service.getDeliveries(senderId, recipientId, startDate, endDate, status);
        List<DeliveryResponse> responses = deliveries.stream()
                .map(delivery -> mapper.map(delivery, DeliveryResponse.class))
                .toList();

        return ResponseEntity.ok(responses);
    }
}
