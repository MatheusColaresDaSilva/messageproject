package com.smsender.enums;

public enum PlanType {

    POS("POSPAGO"),
    PRE("PREPAGO");


    private final String value;

    PlanType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StatusType fromString(String text) {
        for (StatusType b : StatusType.values()) {
            if (b.getValue().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
