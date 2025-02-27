package com.pasan.samples.leadcrm.controller.model;

import jakarta.validation.constraints.NotNull;

public record CreateReservationLegalRequest(
        @NotNull(message = "reservation id is required")
        Integer reservationId,
        @NotNull(message = "contract signed is required")
        Boolean contractSinged,
        String legalNotes
) {
}
