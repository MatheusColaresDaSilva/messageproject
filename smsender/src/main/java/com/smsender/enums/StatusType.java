package com.smsender.enums;

public enum StatusType {

    P("PENDING"),
    S("SUCCESS"),
    E("ERROR");


    private final String value;

    StatusType(String value) {
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
