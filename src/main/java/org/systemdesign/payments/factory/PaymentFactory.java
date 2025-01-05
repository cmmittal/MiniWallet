package org.systemdesign.payments.factory;

import org.systemdesign.payments.CreditCard;
import org.systemdesign.payments.DebitCard;
import org.systemdesign.payments.PaymentMethod;
import org.systemdesign.payments.UPI;

public class PaymentFactory {
    public PaymentMethod getPaymentMethod(String paymentMethod){
        return switch (paymentMethod) {
            case "CC" -> new CreditCard();
            case "DC" -> new DebitCard();
            case "UPI" -> new UPI();
            default -> null;
        };
    }
}
