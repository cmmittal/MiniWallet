package org.systemdesign.payments;

import org.systemdesign.model.User;

public interface PaymentMethod {
    void loadMoney(User userName, int amount);
}
