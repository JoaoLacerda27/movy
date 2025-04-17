package com.movy.application.controller.documentation;

import com.movy.application.controller.request.DeliveryEventRequest;
import com.movy.application.controller.response.DeliveryEventResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@Tag(name = "Delivery Events")
public interface DeliveryEventDoc {
    @Operation(summary = "create a new delivery event")
    ResponseEntity<DeliveryEventResponse> registerEvent(DeliveryEventRequest request);

    @Operation(summary = "get events by delivery id")
    ResponseEntity<List<DeliveryEventResponse>> getEventsByDelivery(UUID id);
}
