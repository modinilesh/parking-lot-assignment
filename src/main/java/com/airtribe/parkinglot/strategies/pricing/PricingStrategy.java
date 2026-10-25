package com.airtribe.parkinglot.strategies.pricing;

import com.airtribe.parkinglot.entities.Ticket;

import java.util.Date;

public interface PricingStrategy {

    double calculatePrice(Ticket ticket, Date exitTime);
}
