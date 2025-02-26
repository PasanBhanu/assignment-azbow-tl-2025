package com.pasan.samples.leadcrm.controller.model;

import java.math.BigDecimal;
import java.util.Date;

public record CreateReservationRequest(
        Integer propertyId,
        Date reservationDate,
        BigDecimal reservationFee,
        Date expectedClosingDate
) {
}
