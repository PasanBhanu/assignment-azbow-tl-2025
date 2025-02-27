package com.pasan.samples.leadcrm.controller.model;

import java.util.List;

public record LeadsResponse(
        List<LeadResponse> leads
) {
}
