package com.airtribe.parkinglot.strategies.payment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CardPayment implements Payment {

    @Override
    public void pay(double amount) {
        log.info("Card payment of {} done", amount);
    }
}
