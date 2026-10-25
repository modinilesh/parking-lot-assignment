package com.airtribe.parkinglot.enums;

public enum PaymentMethod {
    CASH,
    CREDIT_CARD,
    DEBIT_CARD,
    UPI,
    WALLET,
    OTHER;

    private String name;

    public String getName() {
        return name;
    }
}
