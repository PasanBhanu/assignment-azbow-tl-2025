package com.pasan.samples.leadcrm.controller.model;

import com.pasan.samples.leadcrm.repository.model.Lead;
import com.pasan.samples.leadcrm.util.LeadStatus;

import java.math.BigDecimal;
import java.util.Date;

public record LeadResponse(
        Integer id,
        String name,
        String contact,
        String source,
        Date inquiryDate,
        LeadStatus status,
        String followUpStatus,
        String preferredPropertyType,
        BigDecimal budget,
        String notes,
        AgentResponse agent,
        PropertyReservationResponse reservation
) {
    public static LeadResponse of(Lead lead, AgentResponse agent, PropertyReservationResponse reservation) {
        return new LeadResponse(
                lead.getId(),
                lead.getName(),
                lead.getContact(),
                lead.getSource(),
                lead.getInquiryDate(),
                lead.getStatus(),
                lead.getFollowUpStatus(),
                lead.getPreferredPropertyType(),
                lead.getBudget(),
                lead.getNotes(),
                agent,
                reservation
        );
    }
}
