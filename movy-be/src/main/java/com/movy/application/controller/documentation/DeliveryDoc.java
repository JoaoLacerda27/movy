package com.movy.application.controller.documentation;

import com.movy.application.controller.request.DeliveryRequest;
import com.movy.application.controller.response.DeliveryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Deliveries")
public interface DeliveryDoc {
    @Operation(summary = "create a new delivery")
    ResponseEntity<DeliveryResponse> newDelivery(DeliveryRequest request);
}
