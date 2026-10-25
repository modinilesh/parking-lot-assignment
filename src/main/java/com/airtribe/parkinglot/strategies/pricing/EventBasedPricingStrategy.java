package com.airtribe.parkinglot.strategies.pricing;

import com.airtribe.parkinglot.entities.Ticket;
import com.airtribe.parkinglot.enums.VehicleType;

public class EventBasedPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(Ticket ticket) {
        return switch (ticket.getVehicle().getVehicleType()) {
            case BIKE -> 50;
            case CAR -> 100;
            case TRUCK -> 150;
            case BUS -> 200;
        };
    }
}
