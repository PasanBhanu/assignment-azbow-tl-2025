package com.pasan.samples.leadcrm.controller.model;

import java.math.BigDecimal;

public record CreateReservationFinancialApprovalRequest(
        Integer reservationId,
        Boolean financialStatus,
        BigDecimal loanAmount,
        String paymentPlan
) {
}
