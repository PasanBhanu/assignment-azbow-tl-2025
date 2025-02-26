package com.pasan.samples.leadcrm.controller.model;

import java.math.BigDecimal;

public record CreateReservationFinancialApprovalRequest(
        String financialStatus,
        BigDecimal loanAmount,
        String paymentPlan
) {
}
