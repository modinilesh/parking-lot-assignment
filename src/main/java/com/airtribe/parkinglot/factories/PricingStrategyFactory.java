package com.airtribe.parkinglot.factories;

import com.airtribe.parkinglot.strategies.pricing.EventBasedPricingStrategy;
import com.airtribe.parkinglot.strategies.pricing.PricingStrategy;
import com.airtribe.parkinglot.strategies.pricing.TimeBasedPricingStrategy;

public class PricingStrategyFactory {

    public static PricingStrategy createPricingStrategy(com.airtribe.parkinglot.enums.PricingStrategy pricingStrategy) {
        return switch (pricingStrategy) {
            case TIME_BASED -> new TimeBasedPricingStrategy();
            case EVENT_BASED -> new EventBasedPricingStrategy();
        };
    }
}
