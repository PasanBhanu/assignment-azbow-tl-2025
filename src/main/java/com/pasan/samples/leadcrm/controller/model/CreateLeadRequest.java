package com.pasan.samples.leadcrm.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

public record CreateLeadRequest (
        @NotBlank(message = "name is required")
        String name,
        @NotBlank(message = "contact is required")
        String contact,
        @NotBlank(message = "source is required")
        String source,
        @PastOrPresent(message = "inquiry date cannot be in the future")
        Date inquiryDate
) {
}
