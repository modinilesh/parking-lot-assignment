package com.airtribe.parkinglot.enums;

public enum VehicleType {
    BIKE,
    CAR,
    TRUCK,
    BUS

    private String name;

    private VehicleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
