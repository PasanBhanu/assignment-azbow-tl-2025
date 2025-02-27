package com.pasan.samples.leadcrm.controller.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record CreateReservationRequest(
        @NotNull(message = "property id is required")
        Integer propertyId,
        @NotNull(message = "reservation date is required")
        @FutureOrPresent(message = "reservation date must be today or in the future")
        Date reservationDate,
        @NotNull(message = "reservation fee is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "reservation fee must be greater than 0")
        BigDecimal reservationFee,
        @NotNull(message = "expected closing date is required")
        @FutureOrPresent(message = "expected closing date must be today or in the future")
        Date expectedClosingDate
) {
}
