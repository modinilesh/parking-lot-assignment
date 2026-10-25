package com.airtribe.parkinglot.entities;

import lombok.Data;

@Data
public class Ticket {
    private String id;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;
    private ParkingFloor parkingFloor;
    private ParkingLot parkingLot;
    private String entryTime;
    private String exitTime;
    private String status;
    private String createdAt;
    private String updatedAt;
}
