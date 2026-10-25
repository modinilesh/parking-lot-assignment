package com.airtribe.parkinglot.entities;

import lombok.Data;

import java.util.List;

@Data
public class ParkingFloor {

    private long floorId;
    private int floorNumber;
    private List<ParkingSpot> spots;

    //capabilities
        //getAvailableSlots(VehicleType vehicleType)
        //parkVehicle(Vehicle vehicle)
        //unparkVehicle(Ticket ticket)
        //addSlots(Slot slot)
        //getSlots()
}
