package com.movy.application.controller;

import com.movy.application.controller.documentation.DeliveryEventDoc;
import com.movy.application.controller.request.DeliveryEventRequest;
import com.movy.application.controller.response.DeliveryEventResponse;
import com.movy.application.dto.DeliveryEventDTO;
import com.movy.application.service.DeliveryEventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/deliveries")
public class DeliveryEventController implements DeliveryEventDoc {

    private final DeliveryEventService service;
    private final ModelMapper mapper;

    @Override
    @PostMapping("/events")
    public ResponseEntity<DeliveryEventResponse>  registerEvent(@RequestBody @Valid DeliveryEventRequest request) {
        DeliveryEventDTO deliveryEvent = service.registerEvent(mapper.map(request, DeliveryEventDTO.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(deliveryEvent, DeliveryEventResponse.class));
    }

    @Override
    @GetMapping("/{id}/events")
    public ResponseEntity<List<DeliveryEventResponse>> getEventsByDelivery(@PathVariable UUID id) {
        List<DeliveryEventDTO> events = service.findByDeliveryId(id);

        List<DeliveryEventResponse> response = events.stream()
                .map(event -> mapper.map(event, DeliveryEventResponse.class))
                .toList();

        return ResponseEntity.ok(response);
    }
}
