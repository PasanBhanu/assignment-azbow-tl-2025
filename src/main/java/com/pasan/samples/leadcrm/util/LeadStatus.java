package com.pasan.samples.leadcrm.util;

public enum LeadStatus {
    UNASIGNED(0),
    ASSIGNED(1),
    RESERVATION(2),
    FINANCIAL_APPROVED(3),
    LEGAL_FINALIZED(4),
    SALE(5);

    public final int value;

    LeadStatus(int value) {
        this.value = value;
    }

    public static LeadStatus fromValue(int value) {
        for (LeadStatus status : LeadStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid LeadStatus value: " + value);
    }
}
