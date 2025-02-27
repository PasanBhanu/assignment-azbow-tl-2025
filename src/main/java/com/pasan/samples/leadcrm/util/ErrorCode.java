package com.pasan.samples.leadcrm.util;

public enum ErrorCode {
    LEAD_NOT_FOUND("LCRM001", "Lead not found"),
    AGENT_NOT_FOUND("LCRM002", "Agent not found"),
    PROPERTY_NOT_FOUND("LCRM003", "Property not found"),
    RESERVATION_NOT_FOUND("LCRM004", "Reservation not found"),
    SALE_NOT_FOUND("LCRM005", "Sale not found"),
    INELIGIBLE_LEAD_STATUS("LCRM006", "Ineligible lead status");

    public final String errorCode;
    public final String errorDescription;

    ErrorCode(String errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }
}
