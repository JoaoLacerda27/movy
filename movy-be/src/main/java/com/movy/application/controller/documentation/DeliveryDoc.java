package com.movy.application.controller.documentation;

import com.movy.application.controller.request.DeliveryRequest;
import com.movy.application.controller.response.DeliveryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Tag(name = "Deliveries")
public interface DeliveryDoc {
    @Operation(summary = "create a new delivery")
    ResponseEntity<DeliveryResponse> newDelivery(DeliveryRequest request);

    @Operation(summary = "get a delivery by tracking code")
    ResponseEntity<DeliveryResponse> getByTrackingCode(String code);

    @Operation(summary = "Get deliveries by optional filters")
    ResponseEntity<List<DeliveryResponse>> getDeliveries(
            @Parameter(description = "Sender ID") UUID senderId,
            @Parameter(description = "Recipient ID") UUID recipientId,
            @Parameter(description = "Start date (inclusive, ISO 8601 format)") Instant startDate,
            @Parameter(description = "End date (inclusive, ISO 8601 format)") Instant endDate,
            @Parameter(description = "Delivery status (e.g. PENDING, COMPLETED, CANCELLED)") String status
    );
}
