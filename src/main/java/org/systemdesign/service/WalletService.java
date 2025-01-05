package org.systemdesign.service;

import org.systemdesign.model.Transaction;
import org.systemdesign.model.User;
import org.systemdesign.payments.PaymentMethod;
import org.systemdesign.payments.factory.PaymentFactory;
import org.systemdesign.strategy.filter.FilterStrategy;
import org.systemdesign.strategy.sorting.SortStrategy;

import java.util.ArrayList;
import java.util.List;

public class WalletService {
    public UserService userService;

    public WalletService(UserService userService) {
        this.userService = userService;
    }

    public String topUpWallet(String userName, String payment, int amount) {
        PaymentFactory paymentFactory = new PaymentFactory();
        PaymentMethod paymentMethod = paymentFactory.getPaymentMethod(payment);
        for (var user : userService.users) {
            if (user.userName.equals(userName)) {
                paymentMethod.loadMoney(user, amount);
                return userName + "'s Wallet has credited with " + amount + " Rs Successfully";
            }
        }
        return "User " + userName + " is not registered";
    }

    public String fetchBalance(String userName) {
        for (var user : userService.users) {
            if (user.userName.equals(userName)) {
                int userBalance = user.wallet.balance;
                return userName + "'s wallet has " + userBalance + " amount";
            }
        }
        return "User " + userName + " is not registered";
    }

    private User getUser(String userName) {
        for (var u : userService.users) {
            if (u.userName.equals(userName)) {
                return u;
            }
        }
        return null;
    }

    public String sendMoney(String sender, String receiver, int amount) {
        User senderUser = getUser(sender);
        User receiverUser = getUser(receiver);

        if (senderUser == null) {
            return "User " + sender + " is not registered to Send Money";
        }
        if (receiverUser == null) {
            return "User " + receiver + " is not registered to receive Money";
        }

        Transaction transaction = new Transaction(sender, receiver, amount);
        senderUser.wallet.transactions.add(transaction);
        if (senderUser.wallet.balance >= amount) {
            senderUser.wallet.balance -= amount;

            receiverUser.wallet.balance += amount;
            receiverUser.wallet.transactions.add(transaction);
        } else {
            return sender + " doesn’t have sufficient amount to transfer " + amount + " Rs";
        }

        return sender + " has transferred " + amount + " Rs amount to " + receiver;
    }

    public List<String> getTransactions(String userName, String filter, String sorter) {
        List<String> results = new ArrayList<>();
        for (var user : userService.users) {
            if (user.userName.equals(userName)) {
                FilterStrategy filterStrategy = new FilterStrategy();

                List<List<String>> transactions = filterStrategy.filter(userName, user.wallet.transactions, filter);

                SortStrategy sortStrategy = new SortStrategy();

                results = sortStrategy.customSort(transactions, sorter);
            }
        }
        return results;
    }
}
