package org.systemdesign.payments;

import org.systemdesign.model.User;

public class DebitCard implements PaymentMethod{
    public void loadMoney(User userName, int amount){
        userName.wallet.balance+=amount;
    }
}