package com.pasan.samples.leadcrm.controller.model;

public record CreateReservationLegalRequest(
        Integer reservationId,
        Boolean contractSinged,
        String legalNotes
) {
}
