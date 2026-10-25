package com.airtribe.parkinglot.strategies.pricing;

import com.airtribe.parkinglot.entities.Ticket;

import java.util.Date;

public class TimeBasedPricingStrategy implements PricingStrategy{
    @Override
    public double calculatePrice(Ticket ticket, Date exitTime) {
        return 0;
    }
}
