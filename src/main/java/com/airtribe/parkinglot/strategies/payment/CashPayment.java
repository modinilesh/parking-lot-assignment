package com.airtribe.parkinglot.strategies.payment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CashPayment implements Payment {

    @Override
    public void pay(double amount) {
        log.info("Cash payment of {} done", amount);
    }
}
