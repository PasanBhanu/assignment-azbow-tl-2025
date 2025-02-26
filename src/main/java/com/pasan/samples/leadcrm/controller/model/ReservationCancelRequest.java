package com.pasan.samples.leadcrm.controller.model;

public record ReservationCancelRequest(
        Integer reservationId,
        String notes
) {
}
