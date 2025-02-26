package com.pasan.samples.leadcrm.controller.model;

import java.math.BigDecimal;

public record CreateAgentAssignmentRequest(
        Integer agentId,
        String followUpStatus,
        String preferredPropertyType,
        BigDecimal budget,
        String note
) {
}
