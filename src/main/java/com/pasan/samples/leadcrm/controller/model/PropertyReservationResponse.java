package com.pasan.samples.leadcrm.controller.model;

import com.pasan.samples.leadcrm.repository.model.PropertyReservation;

import java.math.BigDecimal;
import java.util.Date;

public record PropertyReservationResponse (
        Integer id,
        Date reservationDate,
        BigDecimal reservationFee,
        Date expectedClosingDate,
        SaleResponse sale,
        Boolean financialApproved,
        BigDecimal loanAmount,
        String paymentPlan,
        Boolean contractSigned,
        String legalNotes
) {
    public static PropertyReservationResponse of(PropertyReservation propertyReservation, SaleResponse sale) {
        return new PropertyReservationResponse(
                propertyReservation.getId(),
                propertyReservation.getReservationDate(),
                propertyReservation.getReservationFee(),
                propertyReservation.getExpectedClosingDate(),
                sale,
                propertyReservation.getFinancialStatus(),
                propertyReservation.getLoanAmount(),
                propertyReservation.getPaymentPlan(),
                propertyReservation.getContractSigned(),
                propertyReservation.getLegalNotes()
        );
    }
}
