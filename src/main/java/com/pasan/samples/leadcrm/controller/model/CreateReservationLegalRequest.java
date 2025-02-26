package com.pasan.samples.leadcrm.controller.model;

public record CreateReservationLegalRequest(
        Boolean contractSinged,
        String legalNotes
) {
}
