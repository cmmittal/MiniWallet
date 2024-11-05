package org.systemdesign.payments;

import org.systemdesign.model.User;
import org.systemdesign.service.UserService;

public class CreditCard implements PaymentMethod {
    public void loadMoney(User userName, int amount){
        userName.wallet.balance+=amount;
    }
}
