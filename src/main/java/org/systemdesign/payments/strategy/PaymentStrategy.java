package org.systemdesign.payments.strategy;

import org.systemdesign.payments.CreditCard;
import org.systemdesign.payments.DebitCard;
import org.systemdesign.payments.PaymentMethod;
import org.systemdesign.payments.UPI;

public class PaymentStrategy {
    public PaymentMethod getPaymentMethod(String paymentMethod){
        return switch (paymentMethod) {
            case "CC" -> new CreditCard();
            case "DC" -> new DebitCard();
            case "UPI" -> new UPI();
            default -> null;
        };
    }
}
