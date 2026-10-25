package com.airtribe.parkinglot.enums;

public enum PricingStrategy {
    EVENT_BASED,
    TIME_BASED;

    private String name;

    public String getName() {
        return name;
    }
}
