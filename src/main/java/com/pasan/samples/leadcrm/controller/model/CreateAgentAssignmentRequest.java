package com.pasan.samples.leadcrm.controller.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateAgentAssignmentRequest(
        @NotNull(message = "agent id is required")
        Integer agentId,
        String followUpStatus,
        String preferredPropertyType,
        @NotNull(message = "budget is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "budget should be greater than 0")
        BigDecimal budget,
        String note
) {
}
