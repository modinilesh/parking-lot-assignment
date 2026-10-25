package com.airtribe.parkinglot.factories;

import com.airtribe.parkinglot.enums.PaymentMethod;
import com.airtribe.parkinglot.strategies.payment.CardPayment;
import com.airtribe.parkinglot.strategies.payment.CashPayment;
import com.airtribe.parkinglot.strategies.payment.Payment;
import com.airtribe.parkinglot.strategies.payment.UpiPayment;

public class PaymentFactory {

    public static Payment createPayment(PaymentMethod paymentMethod) {
        return switch (paymentMethod) {
            case CASH, WALLET, OTHER -> new CashPayment();
            case UPI -> new UpiPayment();
            case CREDIT_CARD, DEBIT_CARD -> new CardPayment();
        };
    }
}
