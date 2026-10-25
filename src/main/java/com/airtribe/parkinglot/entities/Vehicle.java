package com.airtribe.parkinglot.entities;

import com.airtribe.parkinglot.enums.VehicleType;
import lombok.Data;

@Data
public class Vehicle {

    private long vehicleId;
    private VehicleType vehicleType;
    private String vehicleNumber;
    private String ownerName;
    private String ownerPhone;

    public Vehicle (VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Vehicle (VehicleType vehicleType, String vehicleNumber) {
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
    }

    public Vehicle(VehicleType vehicleType, String vehicleNumber, String ownerName) {
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.ownerName = ownerName;
    }
}
