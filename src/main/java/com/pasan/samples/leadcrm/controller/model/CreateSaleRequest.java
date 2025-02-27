package com.pasan.samples.leadcrm.controller.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record CreateSaleRequest(
        @NotNull(message = "reservation id is required")
        Integer reservationId,
        @NotNull(message = "sale date is required")
        @FutureOrPresent(message = "sale date must be today or in the future")
        Date saleDate,
        @NotNull(message = "final sale price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "final sale price must be greater than 0")
        BigDecimal finalSalePrice,
        String commissionDetails
) {
}
