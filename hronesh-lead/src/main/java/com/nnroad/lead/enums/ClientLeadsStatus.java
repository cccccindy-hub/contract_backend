package com.nnroad.lead.enums;

public enum ClientLeadsStatus {
    Ongoing(0, "0", "Ongoing"),
    Converted(1, "1", "Converted"),
    Archived(2, "2", "Archived"),
    OnHold(3, "3", "OnHold"),
    SC_Review(4, "4", "SC Review");

    private final int value;
    private final String strValue;
    private final String name;
    ClientLeadsStatus(int value, String strValue, String name) {
        this.value = value;
        this.strValue = strValue;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getStrValue() {
        return strValue;
    }

    public String getName() {
        return name;
    }
}
