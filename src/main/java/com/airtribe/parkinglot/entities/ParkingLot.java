package com.airtribe.parkinglot.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Data
public class ParkingLot {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private long parkingLotId;
    private String parkingLotName;
    private String location;
    private String parkingLotOwner;
    private List<ParkingFloor> floors;

    public ParkingLot(String parkingLotName, String location, String parkingLotOwner) {
        this.parkingLotName = parkingLotName;
        this.location = location;
        this.parkingLotOwner = parkingLotOwner;
    }

    //capabilities
        //parkVehicle(Vehicle vehicle)
        //unparkVehicle(Ticket ticket)
        //addFloors(ParkingFloor floor)
        //getAvailableSlots()


}
