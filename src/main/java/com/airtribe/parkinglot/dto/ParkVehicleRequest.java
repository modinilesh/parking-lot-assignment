package com.airtribe.parkinglot.dto;

import com.airtribe.parkinglot.enums.VehicleType;

public record ParkVehicleRequest(VehicleType vehicleType, String vehicleNumber, String ownerName, String ownerPhone) {
}
