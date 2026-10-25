package com.airtribe.parkinglot.strategies.pricing;

import com.airtribe.parkinglot.entities.Ticket;

public interface PricingStrategy {

    double calculatePrice(Ticket ticket);
}
