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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
