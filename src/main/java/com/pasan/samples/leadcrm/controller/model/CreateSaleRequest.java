package com.pasan.samples.leadcrm.controller.model;

import java.math.BigDecimal;
import java.util.Date;

public record CreateSaleRequest(
        Integer reservationId,
        Date saleDate,
        BigDecimal finalSalePrice,
        String commissionDetails
) {
}
