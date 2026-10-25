package com.airtribe.parkinglot.entities;

import com.airtribe.parkinglot.enums.VehicleType;
import lombok.Data;
import lombok.Getter;

@Data
public class ParkingSpot {

    private long spotId;
    private VehicleType vehicleType;
    private boolean isAvailable;

    public ParkingSpot(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean canPark(Vehicle vehicle) {
        return this.isAvailable && this.vehicleType == vehicle.getVehicleType();
    }
}
