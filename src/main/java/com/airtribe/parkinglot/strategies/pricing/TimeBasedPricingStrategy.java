package com.airtribe.parkinglot.strategies.pricing;

import com.airtribe.parkinglot.entities.Ticket;
import com.airtribe.parkinglot.enums.VehicleType;

import java.time.Duration;
import java.time.LocalTime;

public class TimeBasedPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(Ticket ticket) {
        long parkedHours = parkedHours(ticket.getEntryTime(), ticket.getExitTime());
        return hourlyRate(ticket.getVehicle().getVehicleType()) * parkedHours;
    }

    private long parkedHours(LocalTime entryTime, LocalTime exitTime) {
        Duration duration = Duration.between(entryTime, exitTime);
        if (duration.isNegative()) {
            duration = duration.plusHours(24);
        }
        return Math.max(1, (duration.toMinutes() + 59) / 60);
    }

    private double hourlyRate(VehicleType vehicleType) {
        return switch (vehicleType) {
            case BIKE -> 10;
            case CAR -> 20;
            case TRUCK -> 30;
            case BUS -> 40;
        };
    }
}
